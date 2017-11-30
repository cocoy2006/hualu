package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.UserComponent;
import com.hualu.main.java.dao.DeviceDao;
import com.hualu.main.java.dao.DeviceReceiveDao;
import com.hualu.main.java.dao.DoctorHealthTaskDao;
import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.HealthTaskDao;
import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.PlanDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.RemindDao;
import com.hualu.main.java.dao.ReportTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.DeviceReceive;
import com.hualu.main.java.entity.DoctorHealthTask;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Plan;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.sms.Sms;

@Service
public class UserService {

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DeviceReceiveDao drDao;

	@Autowired
	private RecordTaskDao rtDao;

	@Autowired
	private NurseRecordTaskDao nrtDao;
	
	@Autowired
	private DoctorRecordTaskDao drtDao;

	@Autowired
	private HealthTaskDao htDao;
	
	@Autowired
	private DoctorHealthTaskDao dhtDao;
	
	@Autowired
	private RemindDao remindDao;
	
	@Autowired
	private PlanDao planDao;
	
	@Autowired
	private UserDao dao;

	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private ReportTaskDao reportTaskDao;

	public int saveOrUpdate(User user) {
		if (user.getCreatetime() == null) {
			user.setCreatetime(Hualu.getStandardTimestamp());
		}
		Date birthday = user.getBirthday();
		if (birthday != null) {
			user.setAge(Hualu.getAge(birthday));
		}
		if(isExist(user.getLoginname(), user.getPhone(), user.getId())) {
			return Status.ERROR_USER_EXIST;
		}
		if(user.getId() == null && user.getPhone() != null) {
			Sms.welcome(user.getPhone());
		}
		dao.saveOrUpdate(user);
		return Status.SUCCESS;
	}
	
	private boolean isExist(String loginname, String phone, Integer id) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.or(Restrictions.eq("loginname", loginname), Restrictions.eq("phone", phone)));
		if(id != null) {
			dc.add(Restrictions.ne("id", id));
		}
		List<User> users = dao.findByCriteria(dc);
		if(users != null && users.size() > 0) {
			return true;
		}
		return false;
	}
	
	public int update(User user, String passwd) {
		user.setPasswd(passwd);
		dao.update(user);
		return Status.SUCCESS;
	}
	
	public int remove(int id) {
		try {
			User user = dao.findById(id);
			if(user != null) {
				unbound(id);
				closeRecordTasks(id);
				closeHealthTasks(id);
				closeReminds(id);
				closePlans(id);
				
				remove(user);
			}
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR;
		}
	}
	
	private void unbound(int id) {
		List<DeviceReceive> drs = drDao.findByUserid(id);
		if(drs != null && drs.size() > 0) {
			DeviceReceive dr = drs.get(0);
			dr.setUserid(null);
			dr.setBoundtime(null);
			dr.setStatus(Status.DEVICE_OCCUPATION);
			drDao.saveOrUpdate(dr);
			// update status of device to 'occupation'
			Device device = deviceDao.findById(dr.getDeviceid());
			device.setDstatus(Status.DEVICE_OCCUPATION);
			deviceDao.saveOrUpdate(device);
		}
	}
	
	private void closeRecordTasks(int id) {
		List<RecordTask> rts = rtDao.findByUserid(id);
		if(rts != null && rts.size() > 0) {
			for(RecordTask rt : rts) {
				rt.setStatus(Status.RecordTask.REMOVED.getInt());
				rtDao.update(rt);
			}
		}
	}
	
	private void closeHealthTasks(int id) {
		List<HealthTask> hts = htDao.findByUserid(id);
		if(hts != null && hts.size() > 0) {
			for(HealthTask ht : hts) {
				ht.setStatus(Status.HealthTask.REMOVED.getInt());
				htDao.update(ht);
			}
		}
	}
	
	private void closeReminds(int id) {
		List<Remind> reminds = remindDao.findByToid(id);
		if(reminds != null && reminds.size() > 0) {
			for(Remind remind : reminds) {
				remind.setStatus(Status.INVALID);
				remindDao.update(remind);
			}
		}
	}
	
	private void closePlans(int id) {
		List<Plan> plans = planDao.findByToid(id);
		if(plans != null && plans.size() > 0) {
			for(Plan plan : plans) {
				plan.setStatus(Status.INVALID);
				planDao.update(plan);
			}
		}
	}
	
	private void remove(User user) {
		user.setStatus(Status.UserStatus.REMOVED.getInt());
		dao.update(user);
	}

	public User signin(String loginname, String passwd) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.or(Restrictions.eq("loginname", loginname), Restrictions.eq("phone", loginname)))
			.add(Restrictions.eq("passwd", passwd))
			.add(Restrictions.ne("status", Status.UserStatus.REMOVED.getInt()));
		List<User> users = dao.findByCriteria(dc);
		if(users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	public User findById(int id) {
		return dao.findById(id);
	}

	public List<User> findAll() {
		return dao.findAll();
	}

	public List<UserComponent> findComponentAll(int doctorid, int userlevel,
			int usergroup, String name, String loginname) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (doctorid != 0) {
			dc.add(Restrictions.eq("doctorid", doctorid));
		}
		if (userlevel != 0) {
			dc.add(Restrictions.eq("userlevel", userlevel));
		}
		if (usergroup != 0) {
			dc.add(Restrictions.eq("usergroup", usergroup));
		}
		if (name != null) {
			dc.add(Restrictions.eq("name", name));
		}
		if (loginname != null) {
			dc.add(Restrictions.eq("loginname", loginname));
		}
		dc.add(Restrictions.ne("status", Status.UserStatus.REMOVED.getInt()));
		List<User> users = dao.findByCriteria(dc);
		if (users != null && users.size() > 0) {
			List<UserComponent> ucs = new ArrayList<UserComponent>();
			for(int i = users.size() - 1; i >= 0; i--) {
				User user = users.get(i);
				UserComponent uc = new UserComponent();
				// fill user first
				uc.setUser(user);
				// find received devices
				List<DeviceReceive> drs = drDao.findByUserid(user.getId());
				if (drs != null && drs.size() > 0) {
					List<Device> devices = new ArrayList<Device>();
					for (DeviceReceive dr : drs) {
						Device device = deviceDao.findById(dr.getDeviceid());
						devices.add(device);
					}
					uc.setDevices(devices);
				}
				// find doctors and fill them
				Set<Integer> idSet = new HashSet<Integer>();
				idSet.add(user.getDoctorid());
				Set<Operator> doctors = new HashSet<Operator>();
				doctors.add(operatorDao.findById(user.getDoctorid()));
				// find record tasks and fill them
				List<RecordTask> rts = rtDao.findByUserid(user.getId());
				if (rts != null && rts.size() > 0) {
					uc.setRts(rts);
					// find doctors
					for (RecordTask rt : rts) {
						DoctorRecordTask drt = drtDao
								.findByTaskid(rt.getId());
						if (drt != null) {
							doctorid = drt.getDoctorid();
							if (!idSet.contains(doctorid)) {
								idSet.add(doctorid);
								doctors.add(operatorDao.findById(doctorid));
							}
						}
					}
				}
				// find health tasks and fill them
				List<HealthTask> hts = htDao.findByUserid(user.getId());
				if (hts != null && hts.size() > 0) {
					uc.setHts(hts);
					// find doctors
					for (HealthTask ht : hts) {
						DoctorHealthTask dht = dhtDao
								.findByTaskid(ht.getDoctorid());
						if (dht != null) {
							doctorid = dht.getDoctorid();
							if (!idSet.contains(doctorid)) {
								idSet.add(doctorid);
								doctors.add(operatorDao.findById(doctorid));
							}
						}
					}
				}
				uc.setDoctors(doctors);
				ucs.add(uc);
			}
			return ucs;
		}
		return null;
	}
	
	public List<RecordTaskComponent> findRecordTaskList(int userid) {
		List<RecordTask> rts = rtDao.findByUserid(userid);
		if(rts != null && rts.size() > 0) {
			List<RecordTaskComponent> rtcs = new ArrayList<RecordTaskComponent>();
			for(int i = rts.size() - 1; i >= 0; i--) {
				RecordTask rt = rts.get(i);
				rtcs.add(createRecordTaskComponent(rt));
			}
			return rtcs;
		}
		return null;
	}
	
	public RecordTaskComponent findRecordTask(int id) {
		RecordTask rt = rtDao.findById(id);
		return createRecordTaskComponent(rt);
	}
	
	private RecordTaskComponent createRecordTaskComponent(RecordTask rt) {
		RecordTaskComponent rtc = new RecordTaskComponent();
		rtc.setRecordTask(rt);
		NurseRecordTask nrt = nrtDao.findByTaskid(rt.getId());
		if(nrt != null) {
			rtc.setNurseRecordTask(nrt);
			Operator nurse = operatorDao.findById(nrt.getNurseid());
			if(nurse != null) {
				rtc.setNurse(nurse);
			}
		}
		DoctorRecordTask drt = drtDao.findByTaskid(rt.getId());
		if(drt != null) {
			rtc.setDoctorRecordTask(drt);
			Operator doctor = operatorDao.findById(drt.getDoctorid());
			if(doctor != null) {
				rtc.setDoctor(doctor);
			}
		}
		return rtc;
	}
	
	public List<HealthTaskComponent> findHealthTaskList(int userid) {
		List<HealthTask> hts = htDao.findByUserid(userid);
		if(hts != null && hts.size() > 0) {
			List<HealthTaskComponent> htcs = new ArrayList<HealthTaskComponent>();
			for(int i = hts.size() - 1; i >= 0; i--) {
				HealthTask ht = hts.get(i);
				htcs.add(createHealthTaskComponent(ht));
			}
			return htcs;
		}
		return null;
	}
	
	public HealthTaskComponent findHealthTask(int id) {
		HealthTask ht = htDao.findById(id);
		return createHealthTaskComponent(ht);
	}
	
	private HealthTaskComponent createHealthTaskComponent(HealthTask ht) {
		HealthTaskComponent htc = new HealthTaskComponent();
		htc.setHealthTask(ht);
		DoctorHealthTask dht = dhtDao.findByTaskid(ht.getId());
		if(dht != null) {
			htc.setDoctorHealthTask(dht);
			// fill doctor
			Operator doctor = operatorDao.findById(dht.getDoctorid());
			if(doctor != null) {
				htc.setDoctor(doctor);
			}
		}
		return htc;
	}
	
	public List<ReportTask> findReportTaskList(int userid) {
		List<ReportTask> rts = reportTaskDao.findByUserid(userid);
		if(rts != null && rts.size() > 0) {
			return rts;
		}
		return null;
	}
	
}
