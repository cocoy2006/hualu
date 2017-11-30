package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.DoctorReportTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * DoctorReportTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.DoctorReportTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DoctorReportTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(DoctorReportTaskDao.class);
	// property constants
	public static final String TASKID = "taskid";
	public static final String DOCTORID = "doctorid";
	public static final String ADVICE = "advice";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorReportTask transientInstance) {
		log.debug("saving DoctorReportTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorReportTask persistentInstance) {
		log.debug("deleting DoctorReportTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorReportTask findById(java.lang.Integer id) {
		log.debug("getting DoctorReportTask instance with id: " + id);
		try {
			DoctorReportTask instance = (DoctorReportTask) getHibernateTemplate()
					.get("com.hualu.main.java.entity.DoctorReportTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorReportTask instance) {
		log.debug("finding DoctorReportTask instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DoctorReportTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorReportTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTaskid(Object taskid) {
		return findByProperty(TASKID, taskid);
	}

	public List findByDoctorid(Object doctorid) {
		return findByProperty(DOCTORID, doctorid);
	}

	public List findByAdvice(Object advice) {
		return findByProperty(ADVICE, advice);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all DoctorReportTask instances");
		try {
			String queryString = "from DoctorReportTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorReportTask merge(DoctorReportTask detachedInstance) {
		log.debug("merging DoctorReportTask instance");
		try {
			DoctorReportTask result = (DoctorReportTask) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorReportTask instance) {
		log.debug("attaching dirty DoctorReportTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorReportTask instance) {
		log.debug("attaching clean DoctorReportTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorReportTaskDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorReportTaskDao) ctx.getBean("DoctorReportTaskDAO");
	}
}