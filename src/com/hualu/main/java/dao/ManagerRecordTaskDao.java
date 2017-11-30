package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.ManagerRecordTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * ManagerRecordTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.ManagerRecordTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ManagerRecordTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(ManagerRecordTaskDao.class);
	// property constants
	public static final String MANAGERID = "managerid";
	public static final String TASKID = "taskid";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(ManagerRecordTask transientInstance) {
		log.debug("saving ManagerRecordTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ManagerRecordTask persistentInstance) {
		log.debug("deleting ManagerRecordTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ManagerRecordTask findById(java.lang.Integer id) {
		log.debug("getting ManagerRecordTask instance with id: " + id);
		try {
			ManagerRecordTask instance = (ManagerRecordTask) getHibernateTemplate()
					.get("com.hualu.main.java.entity.ManagerRecordTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ManagerRecordTask> findByExample(ManagerRecordTask instance) {
		log.debug("finding ManagerRecordTask instance by example");
		try {
			List<ManagerRecordTask> results = (List<ManagerRecordTask>) getHibernateTemplate()
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
		log.debug("finding ManagerRecordTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ManagerRecordTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ManagerRecordTask> findByManagerid(Object managerid) {
		return findByProperty(MANAGERID, managerid);
	}

	public ManagerRecordTask findByTaskid(Object taskid) {
		List<ManagerRecordTask> mrts = findByProperty(TASKID, taskid);
		if(mrts != null && mrts.size() > 0) {
			return mrts.get(0);
		}
		return null;
	}

	public List<ManagerRecordTask> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all ManagerRecordTask instances");
		try {
			String queryString = "from ManagerRecordTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ManagerRecordTask merge(ManagerRecordTask detachedInstance) {
		log.debug("merging ManagerRecordTask instance");
		try {
			ManagerRecordTask result = (ManagerRecordTask) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ManagerRecordTask instance) {
		log.debug("attaching dirty ManagerRecordTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ManagerRecordTask instance) {
		log.debug("attaching clean ManagerRecordTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ManagerRecordTaskDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ManagerRecordTaskDao) ctx.getBean("ManagerRecordTaskDAO");
	}
}