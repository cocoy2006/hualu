package com.hualu.main.java.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.api.DoctorRecordTaskJson;
import com.hualu.main.java.component.api.HealthTaskJson;
import com.hualu.main.java.component.api.NurseRecordTaskJson;
import com.hualu.main.java.component.api.RecordTaskJson;
import com.hualu.main.java.component.api.RemindJson;
import com.hualu.main.java.component.api.ReportTaskJson;
import com.hualu.main.java.component.api.Result;
import com.hualu.main.java.dao.DoctorHealthTaskDao;
import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.HealthTaskDao;
import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.RemindDao;
import com.hualu.main.java.dao.ReportTaskDao;
import com.hualu.main.java.entity.DoctorHealthTask;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.ManagerRecordTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.test.java.component.Record;

@Service
public class ApiService {
	
//	private static final Logger LOG = Logger.getLogger(ApiService.class.getName());
	private static final int SECONDS = 8; // 8 seconds data per page/每页显示8秒钟内容
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private DoctorRecordTaskDao drtDao;
	
	@Autowired
	private ReportTaskDao reportTaskDao;
	
	@Autowired
	private HealthTaskDao htDao;
	
	@Autowired
	private DoctorHealthTaskDao dhtDao;
	
	@Autowired
	private RemindDao remindDao;
	
	public List<RecordTaskJson> recordtasksForOperator(int operatorid, int page) {
		DetachedCriteria operatorDc = null;
		Operator operator = operatorDao.findById(operatorid);
		if(operator != null) {
			if(operator.getRole() == Status.OperatorRole.NURSE.getInt() 
					|| operator.getRole() == Status.OperatorRole.NURSE_LEADER.getInt()) {
				operatorDc = DetachedCriteria.forClass(NurseRecordTask.class);
				operatorDc.add(Restrictions.eq("nurseid", operatorid));
			} else if(operator.getRole() == Status.OperatorRole.NURSE_MANAGER.getInt()) {
				operatorDc = DetachedCriteria.forClass(ManagerRecordTask.class);
				operatorDc.add(Restrictions.eq("managerid", operatorid));
			} else if(operator.getRole() == Status.OperatorRole.DOCTOR.getInt()) {
				operatorDc = DetachedCriteria.forClass(DoctorRecordTask.class);
				operatorDc.add(Restrictions.eq("doctorid", operatorid));
			} else {
				operatorDc = null;
				return null;
			}
			operatorDc.setProjection(Projections.projectionList()
					.add(Property.forName("taskid")));
			DetachedCriteria rtDc = DetachedCriteria.forClass(RecordTask.class);
			rtDc.add(Subqueries.propertyIn("id", operatorDc));
			rtDc = addRtConditionsForOperator(rtDc);
			return getRtJsons(rtDao.findByCriteria(rtDc, (page - 1) * 10, 10));
		}
		return null;
	}
	
	private DetachedCriteria addRtConditionsForOperator(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("userid").as("userid"))
				.add(Property.forName("starttime").as("starttime"))
				.add(Property.forName("endtime").as("endtime"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(RecordTask.class));
		return dc;
	}
	
	public List<RecordTaskJson> recordtasksForUser(int userid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(RecordTask.class);
		RecordTask rt = new RecordTask();
		rt.setUserid(userid);
		dc.add(Example.create(rt));
		dc = addRtConditionsForUser(dc);
		return getRtJsons(rtDao.findByCriteria(dc, (page - 1) * 10, 10));
	}
	
	private DetachedCriteria addRtConditionsForUser(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("starttime").as("starttime"))
				.add(Property.forName("endtime").as("endtime"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(RecordTask.class));
		return dc;
	}
	
	private List<RecordTaskJson> getRtJsons(List<RecordTask> rts) {
		if(rts != null && rts.size() > 0) {
			List<RecordTaskJson> rtJsons = new ArrayList<RecordTaskJson>();
			for(RecordTask rt : rts) {
				RecordTaskJson rtJson = new RecordTaskJson();
				rtJson.setId(rt.getId());
				rtJson.setUserid(rt.getUserid());
				rtJson.setStarttime(rt.getStarttime().toString());
				rtJson.setEndtime(rt.getEndtime().toString());
				rtJson.setStatus(rt.getStatus());
				rtJsons.add(rtJson);
			}
			return rtJsons;
		}
		return null;
	}
	
	public RecordTaskJson recordtask(int id) throws Exception {
		RecordTask rt = rtDao.findById(id);
		if(rt != null) {
			RecordTaskJson rtJson = new RecordTaskJson();
			rtJson.setUserid(rt.getUserid());
			rtJson.setStarttime(rt.getStarttime().toString());
			rtJson.setEndtime(rt.getEndtime().toString());
			Timestamp piecestarttime = rt.getPiecestarttime();
			if(piecestarttime != null) {
				rtJson.setPiecestarttime(piecestarttime.toString());
			}
			Timestamp pieceendtime = rt.getPieceendtime();
			if(pieceendtime != null) {
				rtJson.setPieceendtime(rt.getPieceendtime().toString());
			}
			rtJson.setHeartrate(rt.getHeartrate());
			rtJson.setHeartrateresult(rt.getHeartrateresult());
			rtJson.setBreathrate(rt.getBreathrate());
			rtJson.setBreathrateresult(rt.getBreathrateresult());
			rtJson.setSpo2value(rt.getSpo2value());
			rtJson.setSpo2valueresult(rt.getSpo2valueresult());
			rtJson.setPluserate(rt.getPluserate());
			rtJson.setPluserateresult(rt.getPluserateresult());
			rtJson.setBphigh(rt.getBphigh());
			rtJson.setBplow(rt.getBplow());
			rtJson.setBpavg(rt.getBpavg());
			rtJson.setBpresult(rt.getBpresult());
			rtJson.setBprate(rt.getBprate());
			rtJson.setBprateresult(rt.getBprateresult());
			rtJson.setTemp1(rt.getTemp1());
			rtJson.setTemp1result(rt.getTemp1result());
			// ecgwaveData
			String ids = rt.getEcgwave1ids();
			if(ids != null) {
				if(rt.getPieceids() != null) {
					ids = rt.getPieceids();
				}
				String day = Hualu.getLiteDate(rt.getStarttime().getTime());
				rtJson.setEcgwaveData(getEcgwaveDataForRecordTask(ids, day));
			}
			rtJson.setHour(rt.getHour());
			NurseRecordTask nrt = nrtDao.findByTaskid(id);
			if(nrt != null) {
				String advice = nrt.getAdvice();
				String recalladvice = nrt.getRecalladvice();
				if(advice != null || recalladvice != null) {
					NurseRecordTaskJson nrtJson = new NurseRecordTaskJson();
					nrtJson.setAdvice(advice);
					nrtJson.setRecalladvice(recalladvice);
					rtJson.setNurserecordtask(nrtJson);
				}
			}
			DoctorRecordTask drt = drtDao.findByTaskid(id);
			if(drt != null) {
				String advice = drt.getAdvice();
				String rejectadvice = drt.getRejectadvice();
				if(advice != null || rejectadvice != null) {
					DoctorRecordTaskJson drtJson = new DoctorRecordTaskJson();
					drtJson.setAdvice(advice);
					drtJson.setRejectadvice(rejectadvice);
					rtJson.setDoctorrecordtask(drtJson);
				}
			}
			rtJson.setStatus(rt.getStatus());
			return rtJson;
		}
		return null;
	}
	
	private List<String> getEcgwaveDataForRecordTask(String ids, String day) throws Exception {
		String[] idss = ids.split(",");
		int size = idss.length;
		int diffRecordsCount = size < SECONDS ? size : SECONDS;
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < diffRecordsCount; i++) {
			sb.append(idss[i]).append(",");
		}
		List<String> ecgwaveData = new ArrayList<String>();
		for(Record record : JDBCMySQLService.findRecordTask(day, sb.toString())) {
			ecgwaveData.add(record.getEcgwave1());
		}
		return ecgwaveData;
	}
	
	public List<String> recorddata(int id, int page) throws Exception {
		RecordTask rt = rtDao.findById(id);
		if(rt != null) {
			String ids = rt.getEcgwave1ids();
			if(ids != null) {
				if(rt.getPieceids() != null) {
					ids = rt.getPieceids();
				}
				String[] idss = ids.split(",");
				int size = idss.length;
				int diffRecordsCount = size - ((page - 1) * SECONDS);
				if(diffRecordsCount > SECONDS) {
					diffRecordsCount = SECONDS;
				}
				int startRecord = (page - 1) * SECONDS;
				StringBuffer sb = new StringBuffer();
				for(int i = startRecord; i < startRecord + diffRecordsCount; i++) {
					sb.append(idss[i]).append(",");
				}
				String day = Hualu.getLiteDate(rt.getStarttime().getTime());
				List<String> ecgwaveData = new ArrayList<String>();
				for(Record record : JDBCMySQLService.findRecordTask(day, sb.toString())) {
					ecgwaveData.add(record.getEcgwave1());
				}
				return ecgwaveData;
			}
		}
		return null;
	}
	
	public List<ReportTaskJson> reporttasksForOperator(int operatorid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(ReportTask.class);
		dc.add(Restrictions.eq("doctorid", operatorid));
		dc = addReportTaskConditionsForOperator(dc);
		return getReportTaskJsons(reportTaskDao.findByCriteria(dc, (page - 1) * 10, 10));
	}
	
	private DetachedCriteria addReportTaskConditionsForOperator(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("userid").as("userid"))
				.add(Property.forName("starttime").as("starttime"))
				.add(Property.forName("endtime").as("endtime"))
				.add(Property.forName("type").as("type"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(ReportTask.class));
		return dc;
	}
	
	public List<ReportTaskJson> reporttasksForUser(int userid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(ReportTask.class);
		ReportTask rt = new ReportTask();
		rt.setUserid(userid);
		dc.add(Example.create(rt));
		dc = addReportTaskConditionsForUser(dc);
		return getReportTaskJsons(reportTaskDao.findByCriteria(dc, (page - 1) * 10, 10));
	}
	
	private DetachedCriteria addReportTaskConditionsForUser(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("starttime").as("starttime"))
				.add(Property.forName("endtime").as("endtime"))
				.add(Property.forName("type").as("type"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(ReportTask.class));
		return dc;
	}
	
	private List<ReportTaskJson> getReportTaskJsons(List<ReportTask> rts) {
		if(rts != null && rts.size() > 0) {
			List<ReportTaskJson> rtJsons = new ArrayList<ReportTaskJson>();
			for(ReportTask rt : rts) {
				ReportTaskJson rtJson = new ReportTaskJson();
				rtJson.setId(rt.getId());
				rtJson.setUserid(rt.getUserid());
				rtJson.setStarttime(rt.getStarttime().toString());
				rtJson.setEndtime(rt.getEndtime().toString());
				rtJson.setType(rt.getType());
				rtJson.setStatus(rt.getStatus());
				rtJsons.add(rtJson);
			}
			return rtJsons;
		}
		return null;
	}
	
	public ReportTaskJson reporttask(int id) throws Exception {
		ReportTask rt = reportTaskDao.findById(id);
		if(rt != null) {
			ReportTaskJson rtJson = new ReportTaskJson();
			rtJson.setUserid(rt.getUserid());
			rtJson.setStarttime(rt.getStarttime().toString());
			rtJson.setEndtime(rt.getEndtime().toString());
			rtJson.setHeartrate(rt.getHeartrate());
			rtJson.setHeartrateresult(rt.getHeartrateresult());
			rtJson.setBreathrate(rt.getBreathrate());
			rtJson.setBreathrateresult(rt.getBreathrateresult());
			rtJson.setSpo2value(rt.getSpo2value());
			rtJson.setSpo2valueresult(rt.getSpo2valueresult());
			rtJson.setPluserate(rt.getPluserate());
			rtJson.setPluserateresult(rt.getPluserateresult());
			rtJson.setBphigh(rt.getBphigh());
			rtJson.setBplow(rt.getBplow());
			rtJson.setBpavg(rt.getBpavg());
			rtJson.setBpresult(rt.getBpresult());
			rtJson.setBprate(rt.getBprate());
			rtJson.setBprateresult(rt.getBprateresult());
			rtJson.setTemp1(rt.getTemp1());
			rtJson.setTemp1result(rt.getTemp1result());
			String ids = rt.getIds();
			if(ids != null) {
				rtJson.setIds(ids);
				String day = Hualu.getLiteDate(rt.getStarttime().getTime());
				rtJson.setEcgwaveData(getEcgwaveDataForReportTask(ids, day));
			}
			rtJson.setHour(rt.getHour());
			rtJson.setAdvice(rt.getAdvice());
			rtJson.setStatus(rt.getStatus());
			return rtJson;
		}
		return null;
	}
	
	private List<String> getEcgwaveDataForReportTask(String ids, String day) throws Exception {
		String[] idss = ids.split(",");
		for(String rtId : idss) {
			RecordTask recordTask = rtDao.findById(Integer.valueOf(rtId));
			if(recordTask != null) {
				String subIds = recordTask.getEcgwave1ids();
				if(subIds != null) {
					if(recordTask.getPieceids() != null) {
						subIds = recordTask.getPieceids();
					}
					String[] subIdss = subIds.split(",");
					int size = subIdss.length;
					int diffRecordsCount = size < SECONDS ? size : SECONDS;
					StringBuffer sb = new StringBuffer();
					for(int i = 0; i < diffRecordsCount; i++) {
						sb.append(idss[i]).append(",");
					}
					List<String> ecgwaveData = new ArrayList<String>();
					for(Record record : JDBCMySQLService.findRecordTask(day, sb.toString())) {
						ecgwaveData.add(record.getEcgwave1());
					}
					return ecgwaveData;
				}
			}
		}
		return null;
	}
	
	public List<RemindJson> remindsForOperator(int operatorid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(Remind.class);
		dc.add(Restrictions.eq("fromid", operatorid));
		dc = addRemindConditionsForOperator(dc);
		return getRemindJsons(remindDao.findByCriteria(dc, (page - 1) * 10, 10));
	}
	
	private DetachedCriteria addRemindConditionsForOperator(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("toid").as("toid"))
				.add(Property.forName("starttime").as("starttime"))
				.add(Property.forName("cycle").as("cycle"))
				.add(Property.forName("content").as("content"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(Remind.class));
		return dc;
	}
	
	public List<RemindJson> remindsForUser(int userid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(Remind.class);
		dc.add(Restrictions.eq("toid", userid));
		dc = addRemindConditionsForUser(dc);
		return getRemindJsons(remindDao.findByCriteria(dc, (page - 1) * 10, 10));
	}
	
	private DetachedCriteria addRemindConditionsForUser(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("fromid").as("fromid"))
				.add(Property.forName("starttime").as("starttime"))
				.add(Property.forName("cycle").as("cycle"))
				.add(Property.forName("content").as("content"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(Remind.class));
		return dc;
	}
	
	private List<RemindJson> getRemindJsons(List<Remind> reminds) {
		if(reminds != null && reminds.size() > 0) {
			List<RemindJson> remindJsons = new ArrayList<RemindJson>();
			for(Remind remind : reminds) {
				RemindJson remindJson = new RemindJson();
				remindJson.setId(remind.getId());
				remindJson.setFromid(remind.getFromid());
				remindJson.setToid(remind.getToid());
				remindJson.setStarttime(remind.getStarttime().toString());
				remindJson.setCycle(remind.getCycle());
				remindJson.setContent(remind.getContent());
				remindJson.setStatus(remind.getStatus());
				remindJsons.add(remindJson);
			}
			return remindJsons;
		}
		return null;
	}
	
	public RemindJson remind(int id) {
		Remind remind = remindDao.findById(id);
		if(remind != null) {
			RemindJson remindJson = new RemindJson();
			remindJson.setFromid(remind.getFromid());
			remindJson.setToid(remind.getToid());
			remindJson.setStarttime(remind.getStarttime().toString());
			remindJson.setCycle(remind.getCycle());
			remindJson.setContent(remind.getContent());
			remindJson.setStatus(remind.getStatus());
			return remindJson;
		}
		return null;
	}
	
	public Result read(int id) {
		Remind remind = remindDao.findById(id);
		Result result = new Result();
		if(remind != null) {
			remind.setStatus(Status.REMIND_READ);
			result.setResult(Status.SUCCESS);
		} else {
			result.setResult(Status.ERROR);
		}
		return result;
	}
	
	public List<HealthTaskJson> healthtasksForOperator(int operatorid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(HealthTask.class);
		Operator operator = operatorDao.findById(operatorid);
		if(operator != null) {
			ProjectionList pl = Projections.projectionList();
			if(operator.getRole() == Status.OperatorRole.NURSE.getInt() 
					|| operator.getRole() == Status.OperatorRole.NURSE_LEADER.getInt()
					|| operator.getRole() == Status.OperatorRole.NURSE_MANAGER.getInt()) {
				dc.add(Restrictions.eq("nurseid", operatorid));
				pl.add(Property.forName("doctorid").as("doctorid"));
			} else if(operator.getRole() == Status.OperatorRole.DOCTOR.getInt()) {
				dc.add(Restrictions.eq("doctorid", operatorid));
				pl.add(Property.forName("nurseid").as("nurseid"));
			} else {
				dc = null;
				pl = null;
				return null;
			}
			pl.add(Property.forName("id").as("id"))
				.add(Property.forName("userid").as("userid"))
				.add(Property.forName("createtime").as("createtime"))
				.add(Property.forName("disname").as("disname"))
				.add(Property.forName("status").as("status"));
			dc.setProjection(pl);
			dc.setResultTransformer(Transformers.aliasToBean(HealthTask.class));
			return getHtJsons(htDao.findByCriteria(dc, (page - 1) * 10, 10));
		}
		return null;
	}
	
	public List<HealthTaskJson> healthtasksForUser(int userid, int page) {
		DetachedCriteria dc = DetachedCriteria.forClass(HealthTask.class);
		HealthTask ht = new HealthTask();
		ht.setUserid(userid);
		dc.add(Example.create(ht));
		dc = addHtConditionsForUser(dc);
		return getHtJsons(htDao.findByCriteria(dc, (page - 1) * 10, 10));
	}
	
	private DetachedCriteria addHtConditionsForUser(DetachedCriteria dc) {
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("id").as("id"))
				.add(Property.forName("nurseid").as("nurseid"))
				.add(Property.forName("doctorid").as("doctorid"))
				.add(Property.forName("createtime").as("createtime"))
				.add(Property.forName("disname").as("disname"))
				.add(Property.forName("status").as("status"))
				);
		dc.setResultTransformer(Transformers.aliasToBean(HealthTask.class));
		return dc;
	}
	
	private List<HealthTaskJson> getHtJsons(List<HealthTask> hts) {
		if(hts != null && hts.size() > 0) {
			List<HealthTaskJson> htJsons = new ArrayList<HealthTaskJson>();
			for(HealthTask ht : hts) {
				HealthTaskJson htJson = new HealthTaskJson();
				htJson.setId(ht.getId());
				htJson.setUserid(ht.getUserid());
				htJson.setNurseid(ht.getNurseid());
				htJson.setDoctorid(ht.getDoctorid());
				htJson.setCreatetime(ht.getCreatetime().toString());
				htJson.setDisname(ht.getDisname());
				htJson.setStatus(ht.getStatus());
				htJsons.add(htJson);
			}
			return htJsons;
		}
		return null;
	}
	
	public HealthTaskJson healthtask(int id) {
		HealthTask ht = htDao.findById(id);
		if(ht != null) {
			HealthTaskJson htJson = new HealthTaskJson();
			htJson.setId(ht.getId());
			htJson.setUserid(ht.getUserid());
			htJson.setNurseid(ht.getNurseid());
			htJson.setDoctorid(ht.getDoctorid());
			htJson.setCreatetime(ht.getCreatetime().toString());
			htJson.setDisname(ht.getDisname());
			htJson.setDisscript(ht.getDisscript());
			htJson.setDonetreat(ht.getDonetreat());
			htJson.setDesireaid(ht.getDesireaid());
//			htJson.setAdvice(ht.getAdvice());
			DoctorHealthTask dht  = dhtDao.findByTaskid(ht.getId());
			if(dht != null) {
				htJson.setAdvice(dht.getAdvice());
			}
			htJson.setStatus(ht.getStatus());
			return htJson;
		}
		return null;
	}

}
