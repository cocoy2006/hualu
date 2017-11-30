package com.hualu.main.java.util.timer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.PlanDao;
import com.hualu.main.java.dao.RecordDailyDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.RemindDao;
import com.hualu.main.java.dao.ReportTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Plan;
import com.hualu.main.java.entity.RecordDaily;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.service.JDBCMySQLService;
import com.hualu.main.java.util.Constants;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.jdbc.MySQL;
import com.hualu.main.java.util.recordfsm.Init;
import com.hualu.test.java.component.Record;

/**
 * @author YKK
 *
 */
@Service
public class TimerTask {
	
	private static final Logger LOG = Logger.getLogger(TimerTask.class.getName());
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private PlanDao planDao; 
	
	@Autowired
	private RecordDailyDao rdDao;
	
	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private ReportTaskDao reportTaskDao;
	
	@Autowired
	private RemindDao remindDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private Init init;

	public void updateRecordTask() {
		LOG.log(Level.INFO, " 开始检测自检记录 ");
		try {
			// find id of records have been processed
			// 加载已处理的自检记录ID
			String day = Hualu.getStandardDate();
			String ids = JDBCMySQLService.findIdsOfProcessedRecordTask(day);
			// find record_data have not been processed yet
			// 加载未处理的自检记录
			String sql = "SELECT id, userid, rdatetime FROM ym_record_data_" +
					Hualu.getLiteDate() + " WHERE id NOT IN (" +
					ids + ") AND rdate='" + day + "' ORDER BY userid ASC, rdatetime ASC, id ASC";
			ResultSet rs = MySQL.executeQuery(sql);
			
			Timestamp previousDatetime = null, startDatetime = null;
			int previousUserid = -1;
			StringBuffer sb = new StringBuffer();
			while(rs.next()) {
				int id = rs.getInt("id");
				int userid = rs.getInt("userid");
				Timestamp rdatetime = rs.getTimestamp("rdatetime");
				// set start datetime of record/ 设置自检记录的起止时间
				if(startDatetime == null) {
					startDatetime = rdatetime;
				}
				if(rs.isLast() && Hualu.isNext(rdatetime)) { // 最后一条数据库记录并且超过当前服务器时间N分钟
					// new record task
					LOG.log(Level.INFO, " 检测到新的自检记录 ");
					sb.append(String.valueOf(id)).append(",");
					ids = sb.toString();
					LOG.log(Level.INFO, " 新自检记录的ID序列为: " + ids);
					// save record task 
					int taskid = saveRecordTask(previousUserid, startDatetime, rdatetime, ids);
					LOG.log(Level.INFO, " 新自检记录工单ID为: " + taskid);
					init.execute(taskid);
					startDatetime = rdatetime;
				} else if(rs.isFirst() || (previousUserid == userid && !Hualu.isNext(rdatetime, previousDatetime))) { 
					// 第一条数据库记录或者其他数据库记录但是未超过上一条数据库记录N分钟
					sb.append(String.valueOf(id)).append(",");
				} else {
					// new record task
					LOG.log(Level.INFO, " 检测到新的自检记录 ");
					ids = sb.toString();
					LOG.log(Level.INFO, " 新自检记录的ID序列为: " + ids);
					sb = new StringBuffer();
					sb.append(String.valueOf(id)).append(",");
					// save record task 
					int taskid = saveRecordTask(previousUserid, startDatetime, previousDatetime, ids);
					LOG.log(Level.INFO, " 新自检记录工单ID为: " + taskid);
					init.execute(taskid);
					startDatetime = rdatetime;
				}
				previousDatetime = rdatetime;
				previousUserid = userid;
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}
	}

	public void updateRecordTaskAtZero() {
		LOG.log(Level.INFO, " 开始检测自检记录 ");
		try {
			// find id of records have been processed
			// 加载已处理的自检记录ID
			String day = Hualu.getStandardYesterdayDate();
			String ids = JDBCMySQLService.findIdsOfProcessedRecordTask(day);
			// find record_data have not been processed yet
			// 加载未处理的自检记录
			String sql = "SELECT id, userid, rdatetime FROM ym_record_data_" +
				Hualu.getLiteDate() + " WHERE id NOT IN (" +
				ids + ") AND rdate='" + day + "' ORDER BY userid ASC, rdatetime ASC, id ASC";
			ResultSet rs = MySQL.executeQuery(sql);
			
			Timestamp previousDatetime = null, startDatetime = null;
			int previousUserid = -1;
			StringBuffer sb = new StringBuffer();
			while(rs.next()) {
				int id = rs.getInt("id");
				int userid = rs.getInt("userid");
				Timestamp rdatetime = rs.getTimestamp("rdatetime");
				// set start and end datetime of record/ 设置自检记录的起止时间
				if(startDatetime == null) {
					startDatetime = rdatetime;
				}
				if(rs.isLast()) { // !!!最后一条数据库记录
					// new record task
					LOG.log(Level.INFO, " 检测到新的自检记录 ");
					sb.append(String.valueOf(id)).append(",");
					ids = sb.toString();
					LOG.log(Level.INFO, " 新自检记录的ID序列为: " + ids);
					// save record task 
					int taskid = saveRecordTask(previousUserid, startDatetime, rdatetime, ids);
					LOG.log(Level.INFO, " 新自检记录工单ID为: " + taskid);
					init.execute(taskid);
					startDatetime = rdatetime;
				} else if(rs.isFirst() || (previousUserid == userid && !Hualu.isNext(rdatetime, previousDatetime))) { 
					// 第一条数据库记录或者其他数据库记录但是未超过上一条数据库记录N分钟
					sb.append(String.valueOf(id)).append(",");
				} else {
					// new record task
					LOG.log(Level.INFO, " 检测到新的自检记录 ");
					ids = sb.toString();
					LOG.log(Level.INFO, " 新自检记录的ID序列为: " + ids);
					sb = new StringBuffer();
					sb.append(String.valueOf(id)).append(",");
					// save record task 
					int taskid = saveRecordTask(previousUserid, startDatetime, previousDatetime, ids);
					LOG.log(Level.INFO, " 新自检记录工单ID为: " + taskid);
					init.execute(taskid);
					startDatetime = rdatetime;
				}
				previousDatetime = rdatetime;
				previousUserid = userid;
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}
	}
	
	private int saveRecordTask(int userid, Timestamp starttime,
			Timestamp endtime, String ids) throws Exception {
		RecordTask rt = new RecordTask();
		rt.setUserid(userid);
		rt.setStarttime(starttime);
		rt.setEndtime(endtime);
		rt.setIds(ids);
		String day = Hualu.getLiteDate(starttime.getTime());
//		String[] idss = ids.split(",");
//		int length = idss.length;
		int heartratelength = 0, breathratelength = 0, spo2valuelength = 0, 
			pluseratelength = 0, bplength = 0, bpratelength = 0, temp1length = 0;
		float heartrate = 0, breathrate = 0, spo2value = 0, pluserate = 0, 
			bprate = 0, bphigh = 0, bplow = 0, bpavg = 0, temp1 = 0;
		StringBuffer ecgwave1ids = new StringBuffer();
//			heartrateids = new StringBuffer(), breathrateids = new StringBuffer(),
//			spo2valueids = new StringBuffer(), pluserateids = new StringBuffer(), bpids = new StringBuffer(),
//			bprateids = new StringBuffer(), temp1ids = new StringBuffer();
		boolean recordable = false;
		for (Record record : JDBCMySQLService.findRecordTask(day, ids)) {
			int id = record.getId();
			if(record.getEcgwave1() != null) {
				if(!Hualu.isZeroEcgwave1(record.getEcgwave1()) || recordable) {
					ecgwave1ids.append(id).append(",");
					recordable = true;
				}
			}
			if(Hualu.isLegalData(record.getHeartrate())) {
				heartrate += record.getHeartrate(); // 心率
//				heartrateids.append(id).append(",");
				heartratelength++;
			}
			if(Hualu.isLegalData(record.getBreathrate())) {
				breathrate += record.getBreathrate(); // 呼吸率
//				breathrateids.append(id).append(",");
				breathratelength++;
			}
			if(Hualu.isLegalData(record.getSpo2value())) {
				spo2value += record.getSpo2value(); // 血氧
//				spo2valueids.append(id).append(",");
				spo2valuelength++;
			}
			if(Hualu.isLegalData(record.getPluserate())) {
				pluserate += record.getPluserate(); // 脉率
//				pluserateids.append(id).append(",");
				pluseratelength++;
			}
			if(Hualu.isLegalData(record.getBphigh())) {
				bphigh += record.getBphigh(); // 高压
				bplow += record.getBplow(); // 低压
				bpavg += record.getBpavg(); // 平均压
//				bpids.append(id).append(",");
				bplength++;
			}
			if(Hualu.isLegalData(record.getBprate())) {
				bprate += record.getBprate(); // 血压脉率
//				bprateids.append(id).append(",");
				bpratelength++;
			}
			if(Hualu.isLegalData(record.getTemp1())) {
				temp1 += record.getTemp1(); // 体温
//				temp1ids.append(id).append(",");
				temp1length++;
			}
		}
		if(heartratelength != 0) {
			heartrate /= heartratelength;
		}
		if(breathratelength != 0) {
			breathrate /= breathratelength;
		}
		if(spo2valuelength != 0) {
			spo2value /= spo2valuelength;
		}
		if(pluseratelength != 0) {
			pluserate /= pluseratelength;
		}
		if(bplength != 0) {
			bphigh /= bplength;
			bplow /= bplength;
			bpavg /= bplength;
		}
		if(bpratelength != 0) {
			bprate /= bpratelength;
		}
		if(temp1length != 0) {
			temp1 /= temp1length;
		}
		rt.setEcgwave1ids(ecgwave1ids.length() == 0 ? null : ecgwave1ids.toString());
		LOG.log(Level.INFO, " 新自检记录的心电图ID序列为: " + ecgwave1ids.toString());
		rt.setHeartrate(heartrate);
//		rt.setHeartrateids(heartrateids.toString());
		rt.setBreathrate(breathrate);
//		rt.setBreathrateids(breathrateids.toString());
		rt.setSpo2value(spo2value);
//		rt.setSpo2valueids(spo2valueids.toString());
		rt.setPluserate(pluserate);
//		rt.setPluserateids(pluserateids.toString());
		rt.setBprate(bprate);
//		rt.setBprateids(bprateids.toString());
		rt.setBphigh(bphigh);
		rt.setBplow(bplow);
		rt.setBpavg(bpavg);
		rt.setBpresult(Hualu.getLevelOfBp(bphigh, bplow, bpavg));
//		rt.setBpids(bpids.toString());
		rt.setTemp1(temp1);
//		rt.setTemp1ids(temp1ids.toString());
		int hour = Hualu.getHour(starttime);
		rt.setHour(hour);
		LOG.info(" 自检记录任务创建成功 ");
		return rtDao.save(rt);
	}
	
	public void updateRecordDaily() {
		String yesterday = Hualu.getStandardYesterdayDate();
		Date date = Date.valueOf(yesterday);
		Timestamp starttime = Timestamp.valueOf(yesterday.concat(" 00:00:00.000"));
		Timestamp endtime = Timestamp.valueOf(yesterday.concat(" 23:59:59.999"));
		List<User> users = userDao.findAll();
		for(User user : users) {
			saveRecordDaily(user.getId(), date, starttime, endtime);
		}
	}
	
	private int saveRecordDaily(int userid, Date date, Timestamp starttime, Timestamp endtime) {
		DetachedCriteria dc = DetachedCriteria.forClass(RecordTask.class);
		dc.add(Restrictions.eq("userid", userid))
			.add(Restrictions.ge("starttime", starttime))
			.add(Restrictions.le("endtime", endtime));
		dc.setProjection(Projections.projectionList()
			.add(Property.forName("id"))
			.add(Property.forName("hour")));
		List<Object> list = rtDao.findByCriteria(dc);
		RecordDaily rd = new RecordDaily();
		if(list != null && list.size() > 0) {
			rd.setUserid(userid);
			rd.setDate(date);
			StringBuffer ids = new StringBuffer(), hours = new StringBuffer();
			for(Object obj : list) {
				Object[] objs = (Object[]) obj;
				ids.append(objs[0]).append(",");
				hours.append(objs[1]).append(",");
			}
			rd.setIds(ids.toString());
			rd.setHours(hours.toString());
		} else {
			return Status.ERROR;
		}
		dc.setProjection(Projections.projectionList()
			.add(Property.forName("heartrate").avg())
			.add(Property.forName("breathrate").avg())
			.add(Property.forName("spo2value").avg())
			.add(Property.forName("pluserate").avg())
			.add(Property.forName("bphigh").avg())
			.add(Property.forName("bplow").avg())
			.add(Property.forName("bpavg").avg())
			.add(Property.forName("bprate").avg())
			.add(Property.forName("temp1").avg()));
		list = rtDao.findByCriteria(dc);
		if(list != null && list.size() > 0) {
			Object[] objs = (Object[]) list.get(0);
			rd.setHeartrate(Hualu.parse((Double) objs[0]));
			rd.setBreathrate(Hualu.parse((Double) objs[1]));
			rd.setSpo2value(Hualu.parse((Double) objs[2]));
			rd.setPluserate(Hualu.parse((Double) objs[3]));
			Float bphigh = Hualu.parse((Double) objs[4]);
			Float bplow = Hualu.parse((Double) objs[5]);
			Float bpavg = Hualu.parse((Double) objs[6]);
			rd.setBphigh(bphigh);
			rd.setBplow(bplow);
			rd.setBpavg(bpavg);
			rd.setBpresult(Hualu.getLevelOfBp(bphigh, bplow, bpavg));
			rd.setBprate(Hualu.parse((Double) objs[7]));
			rd.setTemp1(Hualu.parse((Double) objs[8]));
		}
		LOG.info(" 自检记录日统计表创建成功 ");
		return rdDao.save(rd);
	}
	
	public void updateReportTaskMonthly() {
		Timestamp starttime = Hualu.getStarttimeOfLastMonth();
		Timestamp endtime = Hualu.getEndtime();
		List<User> users = userDao.findAll();
		for(User user : users) {
			saveReportTask(user.getId(), starttime, endtime, Status.ReportType.MONTH.getInt());
		}
	}
	
	public void updateReportTaskQuarterly() {
		Timestamp starttime = Hualu.getStarttimeOfLastQuarter();
		Timestamp endtime = Hualu.getEndtime();
		List<User> users = userDao.findAll();
		for(User user : users) {
			saveReportTask(user.getId(), starttime, endtime, Status.ReportType.QUARTER.getInt());
		}
	}
	
	public void updateReportTaskAnnually() {
		Timestamp starttime = Hualu.getStarttimeOfLastYear();
		Timestamp endtime = Hualu.getEndtime();
		List<User> users = userDao.findAll();
		for(User user : users) {
			saveReportTask(user.getId(), starttime, endtime, Status.ReportType.ANNUAL.getInt());
		}
	}
	
	private int saveReportTask(int userid, Timestamp starttime, Timestamp endtime, int type) {
		String startdate = Hualu.getStandardDate(starttime.getTime());
		String enddate = Hualu.getStandardDate(endtime.getTime());
		DetachedCriteria dc = DetachedCriteria.forClass(RecordDaily.class);
		dc.add(Restrictions.eq("userid", userid))
			.add(Restrictions.between("date", startdate, enddate));
		dc.setProjection(Projections.projectionList()
			.add(Property.forName("ids"))
			.add(Property.forName("hours"))
			.add(Property.forName("id")));
		List<Object> list = rdDao.findByCriteria(dc);
		ReportTask reportTask = new ReportTask();
		if(list != null && list.size() > 0) {
			reportTask.setUserid(userid);
			Operator doctor = findDoctor();
			if(doctor != null) {
				reportTask.setDoctorid(doctor.getId());
			}
			reportTask.setCreatetime(Hualu.getStandardTimestamp());
			reportTask.setStarttime(starttime);
			reportTask.setEndtime(endtime);
			reportTask.setType(type);
			StringBuffer ids = new StringBuffer(), dailyids = new StringBuffer();
			Map<Integer, Integer> hour = new HashMap<Integer, Integer>();
			for(int i = 0; i < 24; i++) {
				hour.put(i, 0);
			}
			for(Object obj : list) {
				Object[] objs = (Object[]) obj;
				ids.append(objs[0]);
				String[] hours = objs[1].toString().split(",");
				for(String hourString : hours) {
					int hourInt = Integer.parseInt(hourString);
					int value = hour.get(hourInt);
					hour.put(hourInt, ++value);
				}
				dailyids.append(objs[2]).append(",");
			}
			reportTask.setIds(ids.toString());
			reportTask.setDailyids(dailyids.toString());
			int max = 0;
			for(int i = 0; i < 24; i++) {
				if(hour.get(i) > max) {
					max = i;
				}
			}
			reportTask.setHour(max);
		} else {
			return Status.ERROR;
		}
		dc.setProjection(Projections.projectionList()
			.add(Property.forName("heartrate").avg())
			.add(Property.forName("breathrate").avg())
			.add(Property.forName("spo2value").avg())
			.add(Property.forName("pluserate").avg())
			.add(Property.forName("bphigh").avg())
			.add(Property.forName("bplow").avg())
			.add(Property.forName("bpavg").avg())
			.add(Property.forName("bprate").avg())
			.add(Property.forName("temp1").avg()));
		list = rdDao.findByCriteria(dc);
		if(list != null && list.size() > 0) {
			Object[] objs = (Object[]) list.get(0);
			reportTask.setHeartrate(Hualu.parse((Double) objs[0]));
			reportTask.setBreathrate(Hualu.parse((Double) objs[1]));
			reportTask.setSpo2value(Hualu.parse((Double) objs[2]));
			reportTask.setPluserate(Hualu.parse((Double) objs[3]));
			Float bphigh = Hualu.parse((Double) objs[4]);
			Float bplow = Hualu.parse((Double) objs[5]);
			Float bpavg = Hualu.parse((Double) objs[6]);
			reportTask.setBphigh(bphigh);
			reportTask.setBplow(bplow);
			reportTask.setBpavg(bpavg);
			reportTask.setBpresult(Hualu.getLevelOfBp(bphigh, bplow, bpavg));
			reportTask.setBprate(Hualu.parse((Double) objs[7]));
			reportTask.setTemp1(Hualu.parse((Double) objs[8]));
			reportTask.setStatus(Status.ReportTask.DOCTOR_PROCESSING.getInt());
		}
		LOG.info(" 综合报告任务创建成功 ");
		return reportTaskDao.save(reportTask);
	}
	
	private Operator findDoctor() {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		dc.add(Restrictions.eq("role", Status.OperatorRole.DOCTOR.getInt()))
			.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		List<Operator> doctors = operatorDao.findByCriteria(dc);
		if(doctors != null && doctors.size() > 0) {
			int i = (int) (Math.random() * (doctors.size() - 1));
			return doctors.get(i);
		}
		return null;
	}

	public void updateRemind() {
		DetachedCriteria dc = DetachedCriteria.forClass(Remind.class);
		dc.add(Restrictions.eq("sent", Status.UNSENT))
			.add(Restrictions.ne("status", Status.INVALID));
		List<Remind> reminds = remindDao.findByCriteria(dc);
		if(reminds != null && reminds.size() > 0) {
			for(Remind remind : reminds) {
				Timestamp starttime = remind.getStarttime();
				if(starttime.before(Hualu.getStandardTimestamp())) { // 如果starttime已经到了
					long time = findTimestamp(starttime, remind.getCycle());
					// generate next remind and insert it
					Remind newRemind = remind.clone();
					newRemind.setId(null);
					newRemind.setStarttime(new Timestamp(time));
					remindDao.save(newRemind);
					// remind right now
					remind.setSent(Status.REMIND_SENT);
					remindDao.update(remind);
				}
			}
		}
	}
	
	public void updatePlan() {
		DetachedCriteria dc = DetachedCriteria.forClass(Plan.class);
		dc.add(Restrictions.eq("sent", Status.UNSENT))
			.add(Restrictions.ne("status", Status.INVALID));
		List<Plan> plans = planDao.findByCriteria(dc);
		if(plans != null && plans.size() > 0) {
			for(Plan plan : plans) {
				Timestamp starttime = plan.getStarttime();
				if(starttime.before(Hualu.getStandardTimestamp())) { // 如果starttime已经到了
					long time = findTimestamp(starttime, plan.getCycle());
					Timestamp newTimestamp = new Timestamp(time);
					if(newTimestamp.after(plan.getEndtime())) { // 如果新的时间在endtime之后，则计划已结束
						plan.setStatus(Status.INVALID);
						planDao.update(plan);
						continue;
					} else {
						// generate next plan and insert it
						Plan newPlan = plan.clone();
						newPlan.setId(null);
						newPlan.setStarttime(newTimestamp);
						planDao.save(newPlan);
						// sent plan right now
						plan.setSent(Status.SENT);
						planDao.update(plan);
					}
				}
			}
		}
	}
	
 	private long findTimestamp(Timestamp starttime, int cycle) {
		long time = starttime.getTime();
		if(cycle == Status.CYCLE_EVERYDAY) {
			time += Constants.ONE_DAY;
		} else if(cycle == Status.CYCLE_EVERY_TWO_DAYS) {
			time += Constants.TWO_DAYS;
		} else if(cycle == Status.CYCLE_EVERY_THREE_DAYS) {
			time += Constants.THREE_DAYS;
		} else if(cycle == Status.CYCLE_EVERYWEEK) {
			time += Constants.ONE_WEEK;
		}
		return time;
	}
	
}
