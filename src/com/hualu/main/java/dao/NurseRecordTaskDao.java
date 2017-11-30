package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.NurseRecordTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * NurseRecordTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.NurseRecordTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class NurseRecordTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(NurseRecordTaskDao.class);
	// property constants
	public static final String NURSEID = "nurseid";
	public static final String TASKID = "taskid";
	public static final String ADVICE = "advice";
	public static final String RECALLADVICE = "recalladvice";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(NurseRecordTask transientInstance) {
		log.debug("saving NurseRecordTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NurseRecordTask persistentInstance) {
		log.debug("deleting NurseRecordTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NurseRecordTask findById(java.lang.Integer id) {
		log.debug("getting NurseRecordTask instance with id: " + id);
		try {
			NurseRecordTask instance = (NurseRecordTask) getHibernateTemplate()
					.get("com.hualu.main.java.entity.NurseRecordTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<NurseRecordTask> findByExample(NurseRecordTask instance) {
		log.debug("finding NurseRecordTask instance by example");
		try {
			List<NurseRecordTask> results = (List<NurseRecordTask>) getHibernateTemplate()
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
		log.debug("finding NurseRecordTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NurseRecordTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<NurseRecordTask> findByNurseid(Object nurseid) {
		return findByProperty(NURSEID, nurseid);
	}

	public NurseRecordTask findByTaskid(Object taskid) {
		List<NurseRecordTask> nrts = findByProperty(TASKID, taskid);
		if(nrts != null && nrts.size() > 0) {
			return nrts.get(0);
		}
		return null;
	}

	public List<NurseRecordTask> findByAdvice(Object advice) {
		return findByProperty(ADVICE, advice);
	}

	public List<NurseRecordTask> findByRecalladvice(Object recalladvice) {
		return findByProperty(RECALLADVICE, recalladvice);
	}

	public List<NurseRecordTask> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all NurseRecordTask instances");
		try {
			String queryString = "from NurseRecordTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NurseRecordTask merge(NurseRecordTask detachedInstance) {
		log.debug("merging NurseRecordTask instance");
		try {
			NurseRecordTask result = (NurseRecordTask) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NurseRecordTask instance) {
		log.debug("attaching dirty NurseRecordTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NurseRecordTask instance) {
		log.debug("attaching clean NurseRecordTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NurseRecordTaskDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (NurseRecordTaskDao) ctx.getBean("NurseRecordTaskDAO");
	}
}