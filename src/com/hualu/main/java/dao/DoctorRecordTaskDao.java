package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.DoctorRecordTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * DoctorRecordTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.DoctorRecordTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DoctorRecordTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(DoctorRecordTaskDao.class);
	// property constants
	public static final String DOCTORID = "doctorid";
	public static final String TASKID = "taskid";
	public static final String ADVICE = "advice";
	public static final String REJECTADVICE = "rejectadvice";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorRecordTask transientInstance) {
		log.debug("saving DoctorRecordTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorRecordTask persistentInstance) {
		log.debug("deleting DoctorRecordTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorRecordTask findById(java.lang.Integer id) {
		log.debug("getting DoctorRecordTask instance with id: " + id);
		try {
			DoctorRecordTask instance = (DoctorRecordTask) getHibernateTemplate()
					.get("com.hualu.main.java.entity.DoctorRecordTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DoctorRecordTask> findByExample(DoctorRecordTask instance) {
		log.debug("finding DoctorRecordTask instance by example");
		try {
			List<DoctorRecordTask> results = (List<DoctorRecordTask>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DoctorRecordTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorRecordTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DoctorRecordTask> findByDoctorid(Object doctorid) {
		return findByProperty(DOCTORID, doctorid);
	}

	public DoctorRecordTask findByTaskid(Object taskid) {
		List<DoctorRecordTask> drts = findByProperty(TASKID, taskid);
		if(drts != null && drts.size() > 0) {
			return drts.get(0);
		}
		return null;
	}

	public List<DoctorRecordTask> findByAdvice(Object advice) {
		return findByProperty(ADVICE, advice);
	}

	public List<DoctorRecordTask> findByRejectadvice(Object rejectadvice) {
		return findByProperty(REJECTADVICE, rejectadvice);
	}

	public List<DoctorRecordTask> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all DoctorRecordTask instances");
		try {
			String queryString = "from DoctorRecordTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}