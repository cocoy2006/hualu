package com.hualu.main.java.util.recordfsm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.ManagerRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.entity.ManagerRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class Done extends RecordState {

	@Autowired
	private ManagerRecordTaskDao mrtDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Override
	public void recall(int taskid) {
		id = taskid;
		Operator operator = findManager();
		if(operator != null) {
			saveManagerRecordTask(operator.getId(), id);
			recall();
		}
	}
	
	private Operator findManager() {
		List<Operator> operators = operatorDao.findByRole(Status.OPERATOR_NURSE_MANAGER);
		if(operators != null && operators.size() > 0) {
			int i = (int) (Math.random() * (operators.size() + 1));
			return operators.get(i);
		}
		return null;
	}
	
	private void saveManagerRecordTask(int managerid, int taskid) {
		ManagerRecordTask mrt = mrtDao.findByTaskid(taskid);
		if(mrt == null) {
			mrt = new ManagerRecordTask();
			mrt.setCreatetime(Hualu.getStandardTimestamp());
			mrt.setManagerid(managerid);
			mrt.setTaskid(taskid);
		}
		mrt.setStatus(Status.RecordTask.RECALL.getInt());
		mrtDao.saveOrUpdate(mrt);
	}
	
	private void recall() {
		status = Status.RecordTask.RECALL.getInt();
		setStatus(id);
	}
	
}
