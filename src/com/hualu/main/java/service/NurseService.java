package com.hualu.main.java.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.nurse.DoctorComponent;
import com.hualu.main.java.component.pagination.RecordTaskPagination;
import com.hualu.main.java.dao.DeviceDao;
import com.hualu.main.java.dao.DeviceReceiveDao;
import com.hualu.main.java.dao.DoctorHealthTaskDao;
import com.hualu.main.java.dao.HealthTaskDao;
import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.ReportPictureDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.DeviceReceive;
import com.hualu.main.java.entity.DoctorHealthTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.ReportPicture;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.recordfsm.Done;
import com.hualu.main.java.util.recordfsm.NurseProcessing;

@Service
public class NurseService {
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private DeviceReceiveDao deviceReceiveDao;
	
	@Autowired
	private DoctorHealthTaskDao dhtDao;
	
	@Autowired
	private HealthTaskDao htDao;
	
	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private OperatorDao operatorDao;

	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private ReportPictureDao rpDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NurseProcessing nurseProcessing;
	
	@Autowired
	private Done done;
	
	public Integer findRecordTaskCount(int nurseid, int status) {
		DetachedCriteria dc = getRtDc(nurseid, status);
		dc.setProjection(Projections.rowCount());
		List<Integer> countList = rtDao.findByCriteria(dc);
		if(countList != null && countList.size() > 0) {
			return countList.get(0);
		}
		return new Integer(0);
	}
	
	public List<RecordTaskPagination> findRecordTaskList(int nurseid, int status, int iDisplayStart) {
		DetachedCriteria dc = getRtDc(nurseid, status);
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
	
	private DetachedCriteria getRtDc(int nurseid, int status) {
		DetachedCriteria dc = DetachedCriteria.forClass(RecordTask.class);
		if(status == Status.RecordTask.NURSE_PROCESSING.getInt()
				|| status == Status.RecordTask.DONE.getInt()) {
			dc.add(Restrictions.eq("status", status));
		} else {
			Integer[] notin = {Status.RecordTask.NURSE_PROCESSING.getInt(), 
					Status.RecordTask.DONE.getInt(), Status.RecordTask.REMOVED.getInt()};
			dc.add(Restrictions.not(Restrictions.in("status", notin)));
		}
		dc.add(Subqueries.propertyIn("id", getTaskids(nurseid)));
		return dc;
	}
	
	private DetachedCriteria getTaskids(int nurseid) {
		DetachedCriteria dc = DetachedCriteria.forClass(NurseRecordTask.class);
		dc.add(Restrictions.eq("nurseid", nurseid))
			.setProjection(Projections.projectionList()
					.add(Property.forName("taskid")));
		return dc;
	}
	
	private RecordTaskComponent createRecordTaskComponent(RecordTask rt) {
		RecordTaskComponent rtc = new RecordTaskComponent();
		rtc.setRecordTask(rt);
		User user = userDao.findById(rt.getUserid());
		if(user != null) {
			rtc.setUser(user);
		}
		NurseRecordTask nrt = nrtDao.findByTaskid(rt.getId());
		if(nrt != null) {
			rtc.setNurseRecordTask(nrt);
		}
		return rtc;
	}

	public RecordTaskComponent findRecordTask(int taskid) {
		RecordTask rt = rtDao.findById(taskid);
		return createRecordTaskComponent(rt);
	}
	
	public int nextRecordTask(int id, String piecestarttime, String pieceendtime, String pieceStartIndex, String pieceEndIndex, 
			String pieceStartPage, String pieceEndPage, String ecgwaveLength, String advice) {
		NurseRecordTask nrt = nrtDao.findById(id);
		if(nrt != null) {
			nrt.setAdvice(advice);
			nrt.setEndtime(Hualu.getStandardTimestamp());
			nrtDao.saveOrUpdate(nrt);
			if(piecestarttime != null && !"".equals(piecestarttime)
					&& pieceendtime != null && !"".equals(pieceendtime)) {
				RecordTask rt = rtDao.findById(nrt.getTaskid());
				if(rt != null) {
					rt.setPiecestarttime(Timestamp.valueOf(piecestarttime));
					rt.setPieceendtime(Timestamp.valueOf(pieceendtime));
//					String[] ids = rt.getIds().split(",");
					String[] ids = rt.getEcgwave1ids().split(",");
					StringBuffer pieceids = new StringBuffer();
					int pieceStartIndexInt = Integer.parseInt(pieceStartIndex);
					int pieceEndIndexInt = Integer.parseInt(pieceEndIndex);
					int pieceStartPageInt = Integer.parseInt(pieceStartPage);
					int pieceEndPageInt = Integer.parseInt(pieceEndPage);
					int ecgwaveLengthInt = Integer.parseInt(ecgwaveLength);
					for(int i = Hualu.parseIdIndex(pieceStartIndexInt, pieceStartPageInt, ecgwaveLengthInt) - 1; 
							i < Hualu.parseIdIndex(pieceEndIndexInt, pieceEndPageInt, ecgwaveLengthInt); i++) {
						pieceids.append(ids[i]).append(",");
					}
					rt.setPieceids(pieceids.toString());
					rtDao.saveOrUpdate(rt);
				}
			}
			
			nurseProcessing.execute(nrt.getTaskid());
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public int recallRecordTask(int id, String recalladvice) {
		NurseRecordTask nrt = nrtDao.findById(id);
		if(nrt != null) {
			nrt.setRecalladvice(recalladvice);
			nrt.setEndtime(null);
			nrtDao.saveOrUpdate(nrt);
			
			done.recall(nrt.getTaskid());
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public List<HealthTaskComponent> findHealthTaskList(int nurseid, int status) {
		HealthTask htExample = new HealthTask();
		htExample.setNurseid(nurseid);
		htExample.setStatus(status);
		List<HealthTask> hts = htDao.findByExample(htExample);
		if(hts != null && hts.size() > 0) {
			List<HealthTaskComponent> htcs = new ArrayList<HealthTaskComponent>();
			for(int i = hts.size() - 1; i >= 0; i--) {
				HealthTask ht = hts.get(i);
				HealthTaskComponent htc = new HealthTaskComponent();
				// fill health task
				htc.setHealthTask(ht);
				try {
					// fill user is exist
					User user = userDao.findById(ht.getUserid());
					if(user != null) {
						htc.setUser(user);
					}
					// fill doctor health task
					DoctorHealthTask dht = dhtDao.findByTaskid(ht.getId());
					if(dht != null) {
						htc.setDoctorHealthTask(dht);
						// fill doctor
						Operator doctor = operatorDao.findById(dht.getDoctorid());
						htc.setDoctor(doctor);
					}
				} catch(Exception e) {
					// do nothing if user does not exist
				}
				htcs.add(htc);
			}
			return htcs;
		}
		return null;
	}
	
	public void saveHealthTask(Operator nurse, int userid, int doctorid, String disname,
			String disscript, String donetreat, String desireaid, String cloudrecordid) {
		HealthTask ht = new HealthTask();
		int nurseid = nurse.getId();
		ht.setNurseid(nurseid);
		ht.setUserid(userid);
		ht.setDoctorid(doctorid);
		ht.setCreatetime(Hualu.getStandardTimestamp());
		ht.setDisname(disname);
		ht.setDisscript(disscript);
		ht.setDonetreat(donetreat);
		ht.setDesireaid(desireaid);
		ht.setCloudrecordid(cloudrecordid);
		ht.setStatus(Status.HEALTH_TASK_RUNNING);
		int taskid = htDao.save(ht);
		// new doctor health task record
		DoctorHealthTask dht = new DoctorHealthTask();
		dht.setTaskid(taskid);
		dht.setDoctorid(doctorid);
		dht.setCreatetime(Hualu.getStandardTimestamp());
		dht.setStatus(Status.HEALTH_TASK_RUNNING);
		dhtDao.saveOrUpdate(dht);
	}
	
	public void updateHealthTask(int id, String disname, String disscript,
			String donetreat, String desireaid, String cloudrecordid) {
		HealthTask ht = htDao.findById(id);
		ht.setDisname(disname);
		ht.setDisscript(disscript);
		ht.setDonetreat(donetreat);
		ht.setDesireaid(desireaid);
		ht.setCloudrecordid(cloudrecordid);
		htDao.saveOrUpdate(ht);
	}
	
	public List<DoctorComponent> findDoctors() {
		List<Operator> doctors = operatorDao.findByRole(Status.OPERATOR_DOCTOR);
		if(doctors != null && doctors.size() > 0) {
			List<DoctorComponent> dcs = new ArrayList<DoctorComponent>();
			for(Operator doctor : doctors) {
				DoctorComponent dc = new DoctorComponent();
				// fill doctor first
				dc.setDoctor(doctor);
				// find number of undone doctor health tasks and fill it
				int undoneHealthTaskNumber = 0;
				DoctorHealthTask dhtExample = new DoctorHealthTask();
				dhtExample.setDoctorid(doctor.getId());
				dhtExample.setStatus(Status.HEALTH_TASK_RUNNING);
				List<DoctorHealthTask> dhts = dhtDao.findByExample(dhtExample);
				if(dhts != null && dhts.size() > 0) {
					undoneHealthTaskNumber += dhts.size();
				}
				dhtExample.setStatus(Status.HEALTH_TASK_DRAFT);
				dhts = dhtDao.findByExample(dhtExample);
				if(dhts != null && dhts.size() > 0) {
					undoneHealthTaskNumber += dhts.size();
				}
				dc.setUndoneHealthTaskNumber(undoneHealthTaskNumber);
				dcs.add(dc);
			}
			return dcs;
		}
		return null;
	}
	
	public int bound(int deviceid, int userid) {
		DeviceReceive dr = deviceReceiveDao.findByDeviceid(deviceid);
		if(dr != null) {
			dr.setUserid(userid);
			dr.setBoundtime(Hualu.getStandardTimestamp());
//			dr.setStatus(Status.DEVICE_BOUND);
			deviceReceiveDao.update(dr);
			Device device = deviceDao.findById(deviceid);
			device.setDstatus(Status.DEVICE_BOUND);
			deviceDao.update(device);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public int unbound(int userid) {
		List<DeviceReceive> drs = deviceReceiveDao.findByUserid(userid);
		if(drs != null && drs.size() > 0) {
			DeviceReceive dr = drs.get(0);
			dr.setUserid(null);
			dr.setBoundtime(null);
//			dr.setStatus(Status.DEVICE_OCCUPATION);
			deviceReceiveDao.update(dr);
			// update status of device to 'occupation'
			Device device = deviceDao.findById(dr.getDeviceid());
			device.setDstatus(Status.DEVICE_OCCUPATION);
			deviceDao.update(device);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public void reportUpload(int userid, String boundtime, CommonsMultipartFile file) {
		try {
			String reportPath = Hualu.getServletContext().getRealPath("/upload/report/").concat(File.separator).concat(String.valueOf(userid));
			File report = new File(reportPath);
			if(!report.exists()) {
				report.mkdirs();
			}
			String newFilename = rename(file.getOriginalFilename(), boundtime);
			reportPath = reportPath.concat(File.separator).concat(newFilename);
			report = new File(reportPath);
			file.transferTo(report);
			ReportPicture rp = new ReportPicture();
			rp.setUserid(userid);
			rp.setBoundtime(Hualu.getStandardTimestamp(boundtime));
			if(rpDao.findByExample(rp) == null) {
				rp.setCreatetime(Hualu.getStandardTimestamp());
				rp.setType(Status.ReportType.MONTH.getInt());
				rp.setUrl(newFilename);
				rpDao.saveOrUpdate(rp);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String rename(String originalFilename, String newFilename) {
		int index = originalFilename.indexOf(".");
		if(index > -1) {
			newFilename = newFilename.concat(originalFilename.substring(index, originalFilename.length()));
		}
		return newFilename;
	}
	
}