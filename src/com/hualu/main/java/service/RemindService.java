package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.RemindComponent;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.RemindDao;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class RemindService {
	
	@Autowired
	private OperatorDao operatorDao;

	@Autowired
	private RemindDao remindDao;
	
	public Remind findById(int id) {
		return remindDao.findById(id);
	}

	/**
	 * 只显示已发送(sent=remind_sent)提醒的数量
	 * */
	public int findRemindNumber(int userid, int status) {
		Remind remindExample = new Remind();
		remindExample.setToid(userid);
		remindExample.setStatus(status);
		remindExample.setSent(Status.REMIND_SENT);
		List<Remind> reminds = remindDao.findByExample(remindExample);
		if (reminds != null) {
			return reminds.size();
		}
		return 0;
	}

	public List<RemindComponent> findByUserid(int userid) {
		List<RemindComponent> rcs = new ArrayList<RemindComponent>();
		Remind remindExample = new Remind();
		remindExample.setToid(userid);
		remindExample.setSent(Status.REMIND_SENT);
		// find unread first
		remindExample.setStatus(Status.REMIND_UNREAD);
		List<Remind> reminds = remindDao.findByExample(remindExample);
		if (reminds != null && reminds.size() > 0) {
			for(int i = reminds.size() - 1; i >= 0; i--) {
				Remind remind = reminds.get(i);
				RemindComponent rc = new RemindComponent();
//				remind.setStatus(Status.REMIND_READ);
				rc.setRemind(remind);
				// fill operator
				Operator operator = operatorDao.findById(remind.getFromid());
				if(operator != null) {
					rc.setOperator(operator);
				}
				// add to list
				rcs.add(rc);
			}
//			return rcs;
		}
		// then find read
		remindExample.setStatus(Status.REMIND_READ);
		reminds = remindDao.findByExample(remindExample);
		if (reminds != null && reminds.size() > 0) {
			for (Remind remind : reminds) {
				RemindComponent rc = new RemindComponent();
				rc.setRemind(remind);
				// fill operator
				Operator operator = operatorDao.findById(remind.getFromid());
				if(operator != null) {
					rc.setOperator(operator);
				}
				// add to list
				rcs.add(rc);
			}
//			return rcs;
		}
		return rcs;
	}

	public List<RemindComponent> findByUseridAndStatus(int userid, int status) {
		Remind remindExample = new Remind();
		remindExample.setToid(userid);
		remindExample.setStatus(status);
		remindExample.setSent(Status.REMIND_SENT);
		List<Remind> reminds = remindDao.findByExample(remindExample);
		if (reminds != null && reminds.size() > 0) {
			List<RemindComponent> rcs = new ArrayList<RemindComponent>();
			for(int i = reminds.size() - 1; i >= 0; i--) {
				Remind remind = reminds.get(i);
				RemindComponent rc = new RemindComponent();
				remind.setStatus(Status.REMIND_READ);
				rc.setRemind(remind);
				// fill operator
				Operator operator = operatorDao.findById(remind.getFromid());
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
	
	/**
	 * 只显示状态为status的提醒
	 * */
	public List<Remind> findBySent(int userid, int sent) {
		Remind remindExample = new Remind();
		remindExample.setToid(userid);
		remindExample.setSent(sent);
		return remindDao.findByExample(remindExample);
	}
	
	public void saveOrUpdate(Remind remind) {
		if(remind.getCreatetime() == null) {
			remind.setCreatetime(Hualu.getStandardTimestamp());
		}
		if(remind.getStatus() == null) {
			remind.setStatus(Status.REMIND_UNREAD);
		}
		if(remind.getSent() == null) {
			remind.setSent(Status.REMIND_UNSENT);
		}
		remindDao.saveOrUpdate(remind);
	}

}