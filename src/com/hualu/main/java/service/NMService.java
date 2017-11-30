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

import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.pagination.RecordTaskPagination;
import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.ManagerRecordTaskDao;
import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.ManagerRecordTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.recordfsm.ManagerProcessing;

@Service
public class NMService {
	
	@Autowired
	private DoctorRecordTaskDao drtDao;
	
	@Autowired
	private ManagerRecordTaskDao mrtDao;
	
	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private ManagerProcessing managerProcessing;
	
	public Integer findRecordTaskCount(int managerid, int status) {
		DetachedCriteria dc = getRtDc(managerid, status);
		dc.setProjection(Projections.rowCount());
		List<Integer> countList = rtDao.findByCriteria(dc);
		if(countList != null && countList.size() > 0) {
			return countList.get(0);
		}
		return new Integer(0);
	}

	public List<RecordTaskPagination> findRecordTaskList(int managerid, int status, int iDisplayStart) {
		DetachedCriteria dc = getRtDc(managerid, status);
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
	
	private DetachedCriteria getRtDc(int managerid, int status) {
		DetachedCriteria dc = DetachedCriteria.forClass(RecordTask.class);
		if(status == Status.RecordTask.REJECT.getInt()
				|| status == Status.RecordTask.RECALL.getInt()) {
			dc.add(Restrictions.eq("status", status));
		} else {
			Integer[] notin = {Status.RecordTask.REJECT.getInt(), 
					Status.RecordTask.RECALL.getInt(), Status.RecordTask.REMOVED.getInt()};
			dc.add(Restrictions.not(Restrictions.in("status", notin)));
		}
		dc.add(Subqueries.propertyIn("id", getTaskids(managerid)));
		return dc;
	}
	
	private DetachedCriteria getTaskids(int managerid) {
		DetachedCriteria dc = DetachedCriteria.forClass(ManagerRecordTask.class);
		dc.add(Restrictions.eq("managerid", managerid))
			.setProjection(Projections.projectionList()
					.add(Property.forName("taskid")));
		return dc;
	}
	
	private RecordTaskComponent createRecordTaskComponent(ManagerRecordTask mrt) {
		RecordTask rt = rtDao.findById(mrt.getTaskid());
		RecordTaskComponent rtc = new RecordTaskComponent();
		rtc.setRecordTask(rt);
		rtc.setManagerRecordTask(mrt);
		NurseRecordTask nrt = nrtDao.findByTaskid(rt.getId());
		if(nrt != null) {
			rtc.setNurseRecordTask(nrt);
		}
		DoctorRecordTask drt = drtDao.findByTaskid(rt.getId());
		if(drt != null) {
			rtc.setDoctorRecordTask(drt);
		}
		User user = userDao.findById(rt.getUserid());
		if(user != null) {
			rtc.setUser(user);
		}
		return rtc;
	}
	
	public RecordTaskComponent findRecordTask(int taskid) {
		ManagerRecordTask mrt = mrtDao.findByTaskid(taskid);
		return createRecordTaskComponent(mrt);
	}
	
	public int nextRecordTask(int id) {
		ManagerRecordTask mrt = mrtDao.findById(id);
		if(mrt != null) {
			mrt.setEndtime(Hualu.getStandardTimestamp());
			mrt.setStatus(Status.RecordTask.DONE.getInt());
			mrtDao.saveOrUpdate(mrt);
			// clear piece related data
			RecordTask rt = rtDao.findById(mrt.getTaskid());
			if(rt != null) {
				rt.setPiecestarttime(null);
				rt.setPieceendtime(null);
				rt.setPieceids(null);
				rtDao.saveOrUpdate(rt);
			}
			managerProcessing.execute(mrt.getTaskid());
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public List<Operator> findOperatorsByRole(int role) {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		if(role != 0) {
			dc.add(Restrictions.eq("role", role));
		} else {
			Integer[] roles = {Status.OperatorRole.NURSE.getInt(), Status.OperatorRole.NURSE_LEADER.getInt()};
			dc.add(Restrictions.in("role", roles));
		}
		dc.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		return operatorDao.findByCriteria(dc);
	}
	
}