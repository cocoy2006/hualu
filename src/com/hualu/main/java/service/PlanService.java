package com.hualu.main.java.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.PlanComponent;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.PlanDao;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Plan;
import com.hualu.main.java.util.Constants;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class PlanService {
	
	@Autowired
	private OperatorDao operatorDao;

	@Autowired
	private PlanDao planDao;
	
	public Plan findById(int id) {
		return planDao.findById(id);
	}

	/**
	 * 只显示已发送(sent=plan_sent)提醒的数量
	 * */
	public int findPlanNumber(int userid, int status) {
		Plan planExample = new Plan();
		planExample.setToid(userid);
		planExample.setStatus(status);
		planExample.setSent(Status.SENT);
		List<Plan> plans = planDao.findByExample(planExample);
		if (plans != null) {
			return plans.size();
		}
		return 0;
	}

	/**
	 * 只显示已发送(sent=plan_sent)的提醒
	 * */
	public List<PlanComponent> findByUseridAndStatus(int userid, int status) {
		Plan planExample = new Plan();
		planExample.setToid(userid);
		planExample.setStatus(status);
		planExample.setSent(Status.SENT);
		List<Plan> plans = planDao.findByExample(planExample);
		if (plans != null && plans.size() > 0) {
			List<PlanComponent> rcs = new ArrayList<PlanComponent>();
			for(int i = plans.size() - 1; i >= 0; i--) {
				Plan plan = plans.get(i);
				PlanComponent rc = new PlanComponent();
				plan.setStatus(Status.VALID);
				rc.setPlan(plan);
				// fill operator
				Operator operator = operatorDao.findById(plan.getFromid());
				if(operator != null) {
					rc.setOperator(operator);
				}
				// add to list
				rcs.add(rc);
			}
			return rcs;
		}
		return null;
	}
	
	public List<PlanComponent> findByUserid(int userid) {
		List<PlanComponent> pcs = new ArrayList<PlanComponent>();
		Plan planExample = new Plan();
		planExample.setToid(userid);
		planExample.setSent(Status.SENT);
		// find valid first
		planExample.setStatus(Status.VALID);
		List<Plan> plans = planDao.findByExample(planExample);
		if(plans != null && plans.size() > 0) {
			for(int i = plans.size() - 1; i >= 0; i--) {
				Plan plan = plans.get(i);
				PlanComponent pc = new PlanComponent();
				pc.setPlan(plan);
				// fill operator
				Operator operator = operatorDao.findById(plan.getFromid());
				if(operator != null) {
					pc.setOperator(operator);
				}
				// add to list
				pcs.add(pc);
			}
		}
		// then find invalid
		planExample.setStatus(Status.INVALID);
		plans = planDao.findByExample(planExample);
		if(plans != null && plans.size() > 0) {
			for(int i = plans.size() - 1; i >= 0; i--) {
				Plan plan = plans.get(i);
				PlanComponent pc = new PlanComponent();
				pc.setPlan(plan);
				// fill operator
				Operator operator = operatorDao.findById(plan.getFromid());
				if(operator != null) {
					pc.setOperator(operator);
				}
				// add to list
				pcs.add(pc);
			}
		}
		return pcs;
	}
	
	/**
	 * 只显示状态为sent的提醒
	 * */
	public List<Plan> findBySent(int userid, int sent) {
		Plan planExample = new Plan();
		planExample.setToid(userid);
		planExample.setSent(sent);
		return planDao.findByExample(planExample);
	}
	
	public void saveOrUpdate(Plan plan) {
		if(plan.getCreatetime() == null) {
			plan.setCreatetime(Hualu.getStandardTimestamp());
		}
		if(plan.getEndtime() == null) {
			plan.setEndtime(findEndtime(plan.getStarttime(), plan.getPeriod()));
		}
		if(plan.getStatus() == null) {
			plan.setStatus(Status.VALID);
		}
		if(plan.getSent() == null) {
			plan.setSent(Status.UNSENT);
		}
		planDao.saveOrUpdate(plan);
	}
	
	private Timestamp findEndtime(Timestamp starttime, int period) {
		long time = starttime.getTime();
		if(period == Status.PLAN_PERIOD_ONE_WEEK) {
			time += Constants.ONE_WEEK;
		} else if(period == Status.PLAN_PERIOD_TWO_WEEKS) {
			time += Constants.TWO_WEEKS;
		} else if(period == Status.PLAN_PERIOD_THREE_WEEKS) {
			time += Constants.THREE_WEEKS;
		} else if(period == Status.PLAN_PERIOD_ONE_MONTH) {
			time += Constants.ONE_MONTH;
		} else if(period == Status.PLAN_PERIOD_TWO_MONTHS) {
			time += Constants.TWO_MONTHS;
		} else if(period == Status.PLAN_PERIOD_THREE_MONTHS) {
			time += Constants.THREE_MONTHS;
		}
		return new Timestamp(time);
	}

}