package com.hualu.main.java.util.recordfsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Status;

@Service
public class ManagerProcessing extends RecordState {

	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private OperatorDao operatorDao;

	@Override
	public void execute(int taskid) {
		id = taskid;
		Operator operator = findNurse();
		if(operator != null) {
			next();
		} else {
			waiting();
		}
		setStatus(id);
	}
	
	private Operator findNurse() {
		NurseRecordTask rt = nrtDao.findByTaskid(id);
		if(rt != null) {
			Operator operator = operatorDao.findById(rt.getNurseid());
			if(operator.getStatus() != Status.OperatorStatus.REMOVED.getInt()) {
				return operator;
			}
		}
		return null;
	}
	
	private void next() {
		status = Status.RecordTask.NURSE_PROCESSING.getInt();
	}
	
	private void waiting() {
		status = Status.RecordTask.WAITING_NURSE.getInt();
	}
	
}
