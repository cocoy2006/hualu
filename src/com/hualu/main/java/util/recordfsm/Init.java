package com.hualu.main.java.util.recordfsm;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class Init extends RecordState {
	
	private static final Logger LOG = Logger.getLogger(Init.class.getName());
	
	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private OperatorDao operatorDao;

	@Override
	public void execute(int taskid) {
		id = taskid;
		Operator operator = findNurseOnline();
		if (operator == null) {
			waiting();
		} else {
			saveNurseRecordTask(operator.getId(), id);
			next();
		}
	}
	
	private Operator findNurseOnline() {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		Integer[] role = {Status.OPERATOR_NURSE, Status.OPERATOR_NURSE_LEADER};
		dc.add(Restrictions.eq("isonline", Status.OPERATOR_ONLINE)).add(Restrictions.in("role", role))
			.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		List<Operator> operators = operatorDao.findByCriteria(dc);
		if(operators != null && operators.size() > 0) {
			return operators.get(0);
		}
		return null;
	}

	private void waiting() {
		status = Status.RecordTask.WAITING_NURSE.getInt();
		setStatus(id);
	}
	
	private void saveNurseRecordTask(int nurseid, int taskid) {
		NurseRecordTask nrt = new NurseRecordTask();
		nrt.setNurseid(nurseid);
		nrt.setTaskid(taskid);
		nrt.setCreatetime(Hualu.getStandardTimestamp());
		nrtDao.saveOrUpdate(nrt);
		LOG.info(" 客服自检记录任务创建成功 ");
	}

	private void next() {
		status = Status.RecordTask.NURSE_PROCESSING.getInt();
		setStatus(id);
	}

}
