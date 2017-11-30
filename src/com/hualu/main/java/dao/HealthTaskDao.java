package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.HealthTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * HealthTask entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.HealthTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class HealthTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(HealthTaskDao.class);
	// property constants
	public static final String USERID = "userid";
	public static final String NURSEID = "nurseid";
	public static final String DISNAME = "disname";
	public static final String DISSCRIPT = "disscript";
	public static final String DONETREAT = "donetreat";
	public static final String DESIREAID = "desireaid";
	public static final String CLOUDRECORDID = "cloudrecordid";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public int save(HealthTask transientInstance) {
		log.debug("saving HealthTask instance");
		try {
			log.debug("save successful");
			return (Integer) getHibernateTemplate().save(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(HealthTask persistentInstance) {
		log.debug("deleting HealthTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public HealthTask findById(java.lang.Integer id) {
		log.debug("getting HealthTask instance with id: " + id);
		try {
			HealthTask instance = (HealthTask) getHibernateTemplate().get(
					"com.hualu.main.java.entity.HealthTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(HealthTask instance) {
		log.debug("finding HealthTask instance by example");
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
		log.debug("finding HealthTask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from HealthTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findByNurseid(Object nurseid) {
		return findByProperty(NURSEID, nurseid);
	}

	public List findByDisname(Object disname) {
		return findByProperty(DISNAME, disname);
	}

	public List findByDisscript(Object disscript) {
		return findByProperty(DISSCRIPT, disscript);
	}

	public List findByDonetreat(Object donetreat) {
		return findByProperty(DONETREAT, donetreat);
	}

	public List findByDesireaid(Object desireaid) {
		return findByProperty(DESIREAID, desireaid);
	}

	public List findByCloudrecordid(Object cloudrecordid) {
		return findByProperty(CLOUDRECORDID, cloudrecordid);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all HealthTask instances");
		try {
			String queryString = "from HealthTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public HealthTask merge(HealthTask detachedInstance) {
		log.debug("merging HealthTask instance");
		try {
			HealthTask result = (HealthTask) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(HealthTask instance) {
		log.debug("attaching dirty HealthTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(HealthTask instance) {
		log.debug("attaching clean HealthTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static HealthTaskDao getFromApplicationContext(ApplicationContext ctx) {
		return (HealthTaskDao) ctx.getBean("HealthTaskDAO");
	}
}