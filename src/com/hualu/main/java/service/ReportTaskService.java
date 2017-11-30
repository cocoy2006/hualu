package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.ReportTaskComponent;
import com.hualu.main.java.dao.RecordDailyDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.ReportPictureDao;
import com.hualu.main.java.dao.ReportTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.RecordDaily;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.ReportPicture;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;

@Service
public class ReportTaskService {
	
	@Autowired
	private RecordDailyDao rdDao;
	
	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private ReportTaskDao reportTaskDao;
	
	@Autowired
	private ReportPictureDao rpDao;
	
	@Autowired
	private UserDao userDao;
	
	public ReportTaskComponent findReportTask(int id) {
		ReportTaskComponent rtc = new ReportTaskComponent();
		// find report task and fill it
		ReportTask reportTask = reportTaskDao.findById(id);
		rtc.setReportTask(reportTask);
		// find record daily component list and fill it
		List<RecordDaily> rds = new ArrayList<RecordDaily>();
		String[] rdIds = reportTask.getDailyids().split(",");
		for(String idString : rdIds) {
			int rdId = Integer.parseInt(idString);
			RecordDaily rd = rdDao.findById(rdId);
			if(rd != null) {
				rds.add(rd);
			}
		}
		rtc.setRdList(rds);
		// find record task component list and fill it
		List<RecordTask> rts = new ArrayList<RecordTask>();
		int[] bpCount = new int[7];
		String[] rtIds = reportTask.getIds().split(",");
		for(String idString : rtIds) {
			// fill RecordTaskComponent one by one
			int rtId = Integer.parseInt(idString);
			RecordTask rt = rtDao.findById(rtId);
			if(rt != null) {
				rts.add(rt);
				Integer bpResult = rt.getBpresult();
				if(bpResult != null) {
					bpCount[bpResult - 1]++;
				}
			}
		}
		rtc.setRtList(rts);
		rtc.setBpCount(bpCount);
		// fill user
		User user = userDao.findById(reportTask.getUserid());
		if(user != null) {
			rtc.setUser(user);
			// find report pictures
			DetachedCriteria dc = DetachedCriteria.forClass(ReportPicture.class);
			dc.add(Restrictions.eq("userid", user.getId()))
				.add(Restrictions.between("boundtime", reportTask.getStarttime(), reportTask.getEndtime()));
			List<ReportPicture> rpList = rpDao.findByCriteria(dc);
			rtc.setRpList(rpList);
		}
		return rtc;
	}
	
}
