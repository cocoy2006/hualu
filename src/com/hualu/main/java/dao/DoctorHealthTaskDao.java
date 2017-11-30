package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.DoctorHealthTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * DoctorHealthTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.DoctorHealthTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DoctorHealthTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(DoctorHealthTaskDao.class);
	// property constants
	public static final String TASKID = "taskid";
	public static final String DOCTORID = "doctorid";
	public static final String ADVICE = "advice";
	public static final String STATUS = "status";
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorHealthTask transientInstance) {
		log.debug("saving DoctorHealthTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorHealthTask persistentInstance) {
		log.debug("deleting DoctorHealthTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorHealthTask findById(java.lang.Integer id) {
		log.debug("getting DoctorHealthTask instance with id: " + id);
		try {
			DoctorHealthTask instance = (DoctorHealthTask) getHibernateTemplate()
					.get("com.hualu.main.java.entity.DoctorHealthTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorHealthTask instance) {
		log.debug("finding DoctorHealthTask instance by example");
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
		log.debug("finding DoctorHealthTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorHealthTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

//	public List findByTaskid(Object taskid) {
//		return findByProperty(TASKID, taskid);
//	}
	
	public DoctorHealthTask findByTaskid(Object taskid) {
		List<DoctorHealthTask> dhts = findByProperty(TASKID, taskid);
		if(dhts != null && dhts.size() > 0) {
			return dhts.get(0);
		}
		return null;
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

	public List findByIsdelete(Object isdelete) {
		return findByProperty(ISDELETE, isdelete);
	}

	public List findAll() {
		log.debug("finding all DoctorHealthTask instances");
		try {
			String queryString = "from DoctorHealthTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorHealthTask merge(DoctorHealthTask detachedInstance) {
		log.debug("merging DoctorHealthTask instance");
		try {
			DoctorHealthTask result = (DoctorHealthTask) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorHealthTask instance) {
		log.debug("attaching dirty DoctorHealthTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorHealthTask instance) {
		log.debug("attaching clean DoctorHealthTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorHealthTaskDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorHealthTaskDao) ctx.getBean("DoctorHealthTaskDAO");
	}
}