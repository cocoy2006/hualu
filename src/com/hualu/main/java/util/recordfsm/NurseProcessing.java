package com.hualu.main.java.util.recordfsm;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class NurseProcessing extends RecordState {

	private static final Logger LOG = Logger.getLogger(NurseProcessing.class.getName());
	
	@Autowired
	private DoctorRecordTaskDao drtDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Override
	public void execute(int taskid) {
		id = taskid;
		if(isProcessed()) {
			next();
		} else {
			Operator operator = hasDoctorOnline();
			if (operator == null) {
				waiting();
			} else {
				saveDoctorRecordTask(operator.getId(), id);
				next();
			}
		}
	}
	
	private boolean isProcessed() {
		DoctorRecordTask drt = drtDao.findByTaskid(id);
		return drt != null;
	}
	
	private Operator hasDoctorOnline() {
		Operator operatorExample = new Operator();
		operatorExample.setIsonline(Status.OPERATOR_ONLINE);
		operatorExample.setRole(Status.OPERATOR_DOCTOR);
		List<Operator> operators = operatorDao.findByExample(operatorExample);
		if(operators != null && operators.size() > 0) {
			return operators.get(0);
		}
		return null;
	}
	
	private void waiting() {
		status = Status.RecordTask.WAITING_DOCTOR.getInt();
		setStatus(id);
	}
	
	private void saveDoctorRecordTask(int doctorid, int taskid) {
		DoctorRecordTask drt = new DoctorRecordTask();
		drt.setCreatetime(Hualu.getStandardTimestamp());
		drt.setDoctorid(doctorid);
		drt.setTaskid(taskid);
		drtDao.saveOrUpdate(drt);
	}

	private void next() {
		status = Status.RecordTask.DOCTOR_PROCESSING.getInt();
		setStatus(id);
	}
	
}
