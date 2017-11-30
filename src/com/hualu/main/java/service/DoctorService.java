package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.ReportTaskComponent;
import com.hualu.main.java.component.pagination.RecordTaskPagination;
import com.hualu.main.java.dao.DoctorHealthTaskDao;
import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.HealthTaskDao;
import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.ReportTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.DoctorHealthTask;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.recordfsm.DoctorProcessing;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRecordTaskDao drtDao;
	
	@Autowired
	private DoctorHealthTaskDao dhtDao;
	
	@Autowired
	private HealthTaskDao htDao;
	
	@Autowired
	private ReportTaskDao reportTaskDao;
	
	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DoctorProcessing doctorProcessing;
	
	public Integer findRecordTaskCount(int doctorid, int status) {
		DetachedCriteria dc = getRtDc(doctorid, status);
		dc.setProjection(Projections.rowCount());
		List<Integer> countList = rtDao.findByCriteria(dc);
		if(countList != null && countList.size() > 0) {
			return countList.get(0);
		}
		return new Integer(0);
	}
	
	public List<RecordTaskPagination> findRecordTaskList(int doctorid, int status, int iDisplayStart) {
		DetachedCriteria dc = getRtDc(doctorid, status);
		dc.addOrder(Order.desc("id"));
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("userid").as("userid"))
				.add(Property.forName("endtime").as("endtime"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(RecordTask.class));
		List<RecordTask> rts = rtDao.findByCriteria(dc, iDisplayStart, 10);
		if(rts != null && rts.size() > 0) {
			List<RecordTaskPagination> aaData = new ArrayList<RecordTaskPagination>();
			for(RecordTask rt : rts) {
				RecordTaskPagination rtPagination = new RecordTaskPagination();
				rtPagination.setId(rt.getId());
				rtPagination.setTime(rt.getEndtime().toString());
				Operator nurse = operatorDao.findById(nrtDao.findByTaskid(rt.getId()).getNurseid());
				rtPagination.setNursename(nurse.getName());
				User user = userDao.findById(rt.getUserid());
				if(user != null) {
					rtPagination.setUsername(user.getName());
				} else {
					rtPagination.setUsername("NULL");
				}
				rtPagination.setStatus(Hualu.getInfoOfRecordTaskStatus(rtDao.findById(rt.getId()).getStatus()));
				aaData.add(rtPagination);
			}
			return aaData;
		}
		return null;
	}
	
	private DetachedCriteria getRtDc(int doctorid, int status) {
		DetachedCriteria dc = DetachedCriteria.forClass(RecordTask.class);
		if(status == Status.RecordTask.DOCTOR_PROCESSING.getInt()
				|| status == Status.RecordTask.DOCTOR_DRAFT.getInt()) {
			dc.add(Restrictions.eq("status", status));
		} else {
			Integer[] notin = {Status.RecordTask.DOCTOR_PROCESSING.getInt(), 
					Status.RecordTask.DOCTOR_DRAFT.getInt(), Status.RecordTask.REMOVED.getInt()};
			dc.add(Restrictions.not(Restrictions.in("status", notin)));
		}
		dc.add(Subqueries.propertyIn("id", getTaskids(doctorid)));
		return dc;
	}
	
	private DetachedCriteria getTaskids(int doctorid) {
		DetachedCriteria dc = DetachedCriteria.forClass(DoctorRecordTask.class);
		dc.add(Restrictions.eq("doctorid", doctorid))
			.setProjection(Projections.projectionList()
					.add(Property.forName("taskid")));
		return dc;
	}
	
	public RecordTaskComponent findRecordTask(int taskid) {
		RecordTaskComponent rtc = new RecordTaskComponent();
		// fill record task
		RecordTask rt = rtDao.findById(taskid);
		rtc.setRecordTask(rt);
		// fill user
		User user = userDao.findById(rt.getUserid());
		if(user != null) {
			rtc.setUser(user);
		}
		// fill nurse record task
		NurseRecordTask nrt = nrtDao.findByTaskid(taskid);
		if(nrt != null) {
			rtc.setNurseRecordTask(nrt);
			// fill nurse
			int nurseid = nrt.getNurseid();
			Operator nurse = operatorDao.findById(nurseid);
			rtc.setNurse(nurse);
		}
		// fill doctor record task
		DoctorRecordTask drt = drtDao.findByTaskid(taskid);
		if(drt != null) {
			rtc.setDoctorRecordTask(drt);
		}
		return rtc;
	}
	
	public int nextRecordTask(int id, String advice) {
		DoctorRecordTask drt = drtDao.findById(id);
		if(drt != null) {
			drt.setAdvice(advice);
			drt.setEndtime(Hualu.getStandardTimestamp());
			drtDao.saveOrUpdate(drt);
			
			doctorProcessing.execute(drt.getTaskid());
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public int draftRecordTask(int id, String advice) {
		DoctorRecordTask drt = drtDao.findById(id);
		if(drt != null) {
			drt.setAdvice(advice);
			drt.setEndtime(Hualu.getStandardTimestamp());
			drtDao.saveOrUpdate(drt);
			
			doctorProcessing.draft(drt.getTaskid());
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public int rejectRecordTask(int id, String rejectadvice) {
		DoctorRecordTask drt = drtDao.findById(id);
		if(drt != null) {
			drt.setRejectadvice(rejectadvice);
			drt.setEndtime(Hualu.getStandardTimestamp());
			drtDao.saveOrUpdate(drt);
			
			doctorProcessing.reject(drt.getTaskid());
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}

	public List<HealthTaskComponent> findHealthTaskList(int doctorid, int status) {
		DoctorHealthTask dhtExample = new DoctorHealthTask();
		dhtExample.setDoctorid(doctorid);
		dhtExample.setStatus(status);
		List<DoctorHealthTask> dhts = dhtDao.findByExample(dhtExample);
		if(dhts != null && dhts.size() > 0) {
			List<HealthTaskComponent> htcs = new ArrayList<HealthTaskComponent>();
			for(int i = dhts.size() - 1; i >= 0; i--) {
				DoctorHealthTask dht = dhts.get(i);
				HealthTaskComponent htc = new HealthTaskComponent();
				// fill doctor health task first
				htc.setDoctorHealthTask(dht);
				// find health task and fill it
				int taskid = dht.getTaskid();
				HealthTask ht = htDao.findById(taskid);
				htc.setHealthTask(ht);
				// find user and fill it
				int userid = ht.getUserid();
				User user = userDao.findById(userid);
				htc.setUser(user);
				// find nurse and fill it
				int nurseid = ht.getNurseid();
				Operator nurse = operatorDao.findById(nurseid);
				htc.setNurse(nurse);
				// one component done
				htcs.add(htc);
			}
			return htcs;
		}
		return null;
	}
	
	public HealthTaskComponent findHealthTask(int doctorHealthTaskId) {
		HealthTaskComponent htc = new HealthTaskComponent();
		// find doctor health task and fill it
		DoctorHealthTask dht = dhtDao.findById(doctorHealthTaskId);
		htc.setDoctorHealthTask(dht);
		// find health task and fill it
		HealthTask ht = htDao.findById(dht.getTaskid());
		htc.setHealthTask(ht);
		// fill user
		User user = userDao.findById(ht.getUserid());
		htc.setUser(user);
		// fill nurse
		Operator nurse = operatorDao.findById(ht.getNurseid());
		htc.setNurse(nurse);
		return htc;
	}
	
	/**
	 * @param id
	 *            Integer DoctorHealthTask's ID
	 * @param advice
	 *            String advice
	 * @param mode
	 *            Integer 1 stand for reject, 2 for submit, 3 for draft
	 * @return result Integer
	 * */
	public int updateHealthTask(int id, String advice, int mode) {
		DoctorHealthTask dht = dhtDao.findById(id);
		if(dht != null) {
			dht.setAdvice(advice);
			dht.setEndtime(Hualu.getStandardTimestamp());
			int taskid = dht.getTaskid();
			HealthTask task = htDao.findById(taskid);
			switch(mode) {
				case 1:
					break;
				case 2:
					// set 'doctor_health_task' 's status to 'done'
					// 设置'doctor_health_task'状态为'done'
					dht.setStatus(Status.HEALTH_TASK_DONE);
					// set 'health_task' 's status to 'done'
					// 设置'health_task'状态为'done'
					task.setStatus(Status.HEALTH_TASK_DONE);
					break;
				case 3:
					dht.setStatus(Status.HEALTH_TASK_DRAFT);
					task.setStatus(Status.HEALTH_TASK_DRAFT);
					break;
			}
			dhtDao.saveOrUpdate(dht);
			htDao.saveOrUpdate(task);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public void saveOrUpdateHealthTask(DoctorHealthTask dht) {
		dhtDao.saveOrUpdate(dht);
	}

	public List<ReportTaskComponent> findReportTaskList(int doctorid, int status) {
		ReportTask rtExample = new ReportTask();
		rtExample.setDoctorid(doctorid);
		rtExample.setStatus(status);
		List<ReportTask> rts = reportTaskDao.findByExample(rtExample);
		if(rts != null && rts.size() > 0) {
			List<ReportTaskComponent> rtcs = new ArrayList<ReportTaskComponent>();
			for(int i = rts.size() - 1; i >= 0; i--) {
				ReportTask rt = rts.get(i);
				ReportTaskComponent rtc = new ReportTaskComponent();
				// find report task and fill it
				rtc.setReportTask(rt);
				// find user and fill it
				int userid = rt.getUserid();
				User user = userDao.findById(userid);
				if(user != null) {
					rtc.setUser(user);
				}
				// one component done
				rtcs.add(rtc);
			}
			return rtcs;
		}
		return null;
	}
	
	public int nextReportTask(int id, String advice) {
		ReportTask rt = reportTaskDao.findById(id);
		if(rt != null) {
			rt.setAdvice(advice);
			rt.setStatus(Status.ReportTask.DONE.getInt());
			reportTaskDao.saveOrUpdate(rt);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public int draftReportTask(int id, String advice) {
		ReportTask rt = reportTaskDao.findById(id);
		if(rt != null) {
			rt.setAdvice(advice);
			rt.setStatus(Status.ReportTask.DOCTOR_DRAFT.getInt());
			reportTaskDao.saveOrUpdate(rt);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}

}