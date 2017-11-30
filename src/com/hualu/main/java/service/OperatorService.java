package com.hualu.main.java.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.DeviceReceiveDao;
import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.HealthTaskDao;
import com.hualu.main.java.dao.ManagerRecordTaskDao;
import com.hualu.main.java.dao.NurseRecordTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.ReportTaskDao;
import com.hualu.main.java.entity.DeviceReceive;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.ManagerRecordTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.recordfsm.WaitingDoctor;
import com.hualu.main.java.util.recordfsm.WaitingNurse;

@Service
public class OperatorService {
	
	@Autowired
	private DeviceReceiveDao drDao;
	
	@Autowired
	private RecordTaskDao rtDao;

	@Autowired
	private NurseRecordTaskDao nrtDao;

	@Autowired
	private ManagerRecordTaskDao mrtDao;
	
	@Autowired
	private DoctorRecordTaskDao drtDao;
	
	@Autowired
	private HealthTaskDao htDao;
	
	@Autowired
	private ReportTaskDao reportTaskDao;
	
	@Autowired
	private OperatorDao dao;

	@Autowired
	private WaitingNurse waitingNurse;
	
	@Autowired
	private WaitingDoctor waitingDoctor;

	public Operator signin(String loginname, String passwd, int role) {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		dc.add(Restrictions.or(Restrictions.eq("loginname", loginname), Restrictions.eq("mobile", loginname)))
			.add(Restrictions.eq("passwd", passwd)).add(Restrictions.eq("role", role))
			.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		List<Operator> operators = dao.findByCriteria(dc);
		if(operators != null && operators.size() > 0) {
			Operator operator = operators.get(0);
			// if is nurse, assign the record tasks with status 'waiting_nurse' to it
			int status;
			if(operator.getRole() == Status.OPERATOR_NURSE
					|| operator.getRole() == Status.OPERATOR_NURSE_LEADER) {
				// first find the tasks
				status = Status.RecordTask.WAITING_NURSE.getInt();
				List<RecordTask> rts = rtDao.findByStatus(status);
				if(rts != null && rts.size() > 0) {
					// then create nurse_record_task
					for(RecordTask rt : rts) {
						NurseRecordTask nrt = new NurseRecordTask();
						nrt.setCreatetime(Hualu.getStandardTimestamp());
						nrt.setNurseid(operator.getId());
						nrt.setTaskid(rt.getId());
						nrt.setStatus(Status.RecordTask.NURSE_PROCESSING.getInt());
						nrtDao.saveOrUpdate(nrt);
						
						waitingNurse.execute(rt.getId());
					}
				}
			} else if(operator.getRole() == Status.OPERATOR_DOCTOR) {
				// if is doctor, assign the record tasks with status 'waiting_doctor' to it
				// first find the tasks
				status = Status.RecordTask.WAITING_DOCTOR.getInt();
				List<RecordTask> rts = rtDao.findByStatus(status);
				if(rts != null && rts.size() > 0) {
					// then create doctor_record_task
					for(RecordTask rt : rts) {
						DoctorRecordTask drt = new DoctorRecordTask();
						drt.setCreatetime(Hualu.getStandardTimestamp());
						drt.setDoctorid(operator.getId());
						drt.setTaskid(rt.getId());
						drt.setStatus(Status.RecordTask.DOCTOR_PROCESSING.getInt());
						drtDao.saveOrUpdate(drt);
						
						waitingDoctor.execute(rt.getId());
					}
				}
			}
			// set operator's isonline to 'online'/ 设置为'online'
			operator.setIsonline(Status.OPERATOR_ONLINE);
			dao.saveOrUpdate(operator);
			return operator; 
		}
		return null;
	}
	
	public Operator findById(int id) {
		return dao.findById(id);
	}
	
	public List<Operator> findByRole(int role) {
		return dao.findByRole(role);
	}
	
	public List<Operator> findAll() {
		return dao.findAll();
	}
	
	public List<Operator> findNurses() {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		Integer[] roles = {Status.OperatorRole.NURSE.getInt(), Status.OperatorRole.NURSE_LEADER.getInt()};
		dc.add(Restrictions.in("role", roles))
			.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		return dao.findByCriteria(dc);
	}
	
	public void saveOrUpdate(Operator operator) {
		dao.saveOrUpdate(operator);
	}
	
	public int update(Operator operator, String passwd) {
		if(operator != null) {
			operator.setPasswd(passwd);
			dao.update(operator);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}
	
	public int remove(int id) {
		Operator operator = dao.findById(id);
		if(operator != null) {
			if(operator.getRole() == Status.OperatorRole.DM.getInt()) {
				// delete it directly
				operator.setStatus(Status.OperatorStatus.REMOVED.getInt());
				dao.saveOrUpdate(operator);
			} else if(operator.getRole() == Status.OperatorRole.NURSE.getInt()
					|| operator.getRole() == Status.OperatorRole.NURSE_LEADER.getInt()) {
				if(!isNurseDeleteable(id)) {
					return Status.ERROR;
				}
			} else if(operator.getRole() == Status.OperatorRole.NURSE_MANAGER.getInt()) {
				if(!isManagerDeleteable(id)) {
					return Status.ERROR;
				}
			} else if(operator.getRole() == Status.OperatorRole.DOCTOR.getInt()) {
				if(!isDoctorDeleteable(id)) {
					return Status.ERROR;
				}
			} else {}
		}
		return Status.SUCCESS;
	}
	
	private boolean isNurseDeleteable(int id) {
		if(hasNrtUndone(id) || hasNhtUndone(id) || hasDr(id)) {
			return false;
		}
		return true;
	}
	
	private boolean hasNrtUndone(int id) {
		List<NurseRecordTask> nrts = nrtDao.findByNurseid(id);
		if(nrts != null && nrts.size() > 0) {
			for(NurseRecordTask nrt : nrts) {
				RecordTask rt = rtDao.findById(nrt.getTaskid());
				if(rt.getStatus() == Status.RecordTask.NURSE_PROCESSING.getInt()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasNhtUndone(int id) {
		DetachedCriteria dc = DetachedCriteria.forClass(HealthTask.class);
		dc.add(Restrictions.eq("nurseid", id)).add(Restrictions.eq("status", Status.HealthTask.RUNNING.getInt()));
		List<HealthTask> hts = htDao.findByCriteria(dc);
		if(hts != null && hts.size() > 0) {
			return true;
		}
		return false;
	}
	
	private boolean hasDr(int id) {
		List<DeviceReceive> drs = drDao.findByNurseid(id);
		if(drs != null && drs.size() > 0) {
			return true;
		}
		return false;
	}
	
	private boolean isManagerDeleteable(int id) {
		if(hasMrtUndone(id)) {
			return false;
		}
		return true;
	}
	
	private boolean hasMrtUndone(int id) {
		List<ManagerRecordTask> mrts = mrtDao.findByManagerid(id);
		if(mrts != null && mrts.size() > 0) {
			for(ManagerRecordTask mrt : mrts) {
				RecordTask rt = rtDao.findById(mrt.getTaskid());
				if(rt.getStatus() == Status.RecordTask.REJECT.getInt()
						|| rt.getStatus() == Status.RecordTask.RECALL.getInt()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isDoctorDeleteable(int id) {
		if(hasDrtUndone(id) || hasDhtUndone(id) || hasDoctorReportTaskUndone(id)) {
			return false;
		}
		return true;
	}
	
	private boolean hasDrtUndone(int id) {
		List<DoctorRecordTask> drts = drtDao.findByDoctorid(id);
		if(drts != null && drts.size() > 0) {
			for(DoctorRecordTask drt : drts) {
				RecordTask rt = rtDao.findById(drt.getTaskid());
				if(rt.getStatus() == Status.RecordTask.DOCTOR_PROCESSING.getInt()
						|| rt.getStatus() == Status.RecordTask.DOCTOR_DRAFT.getInt()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasDhtUndone(int id) {
		DetachedCriteria dc = DetachedCriteria.forClass(HealthTask.class);
		Integer[] status = {Status.HealthTask.RUNNING.getInt(), Status.HealthTask.DRAFT.getInt()};
		dc.add(Restrictions.eq("doctorid", id)).add(Restrictions.in("status", status));
		List<HealthTask> hts = htDao.findByCriteria(dc);
		if(hts != null && hts.size() > 0) {
			return true;
		}
		return false;
	}
	
	private boolean hasDoctorReportTaskUndone(int id) {
		DetachedCriteria dc = DetachedCriteria.forClass(ReportTask.class);
		Integer[] status = {Status.ReportTask.DOCTOR_PROCESSING.getInt(), Status.ReportTask.DOCTOR_DRAFT.getInt()};
		dc.add(Restrictions.eq("doctorid", id)).add(Restrictions.in("status", status));
		List<ReportTask> rts = reportTaskDao.findByCriteria(dc);
		if(rts != null && rts.size() > 0) {
			return true;
		}
		return false;
	}
	
}
