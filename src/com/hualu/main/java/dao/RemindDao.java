package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.Remind;

/**
 * A data access object (DAO) providing persistence and search support for
 * Remind entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.Remind
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RemindDao extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(RemindDao.class);
	// property constants
	public static final String FROMID = "fromid";
	public static final String TOID = "toid";
	public static final String CYCLE = "cycle";
	public static final String WAY = "way";
	public static final String CONTENT = "content";
	public static final String STATUS = "status";
	public static final String SENT = "sent";

	protected void initDao() {
		// do nothing
	}

	public void save(Remind transientInstance) {
		log.debug("saving Remind instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Remind persistentInstance) {
		log.debug("deleting Remind instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Remind findById(java.lang.Integer id) {
		log.debug("getting Remind instance with id: " + id);
		try {
			Remind instance = (Remind) getHibernateTemplate().get(
					"com.hualu.main.java.entity.Remind", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Remind instance) {
		log.debug("finding Remind instance by example");
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
		log.debug("finding Remind instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Remind as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFromid(Object fromid) {
		return findByProperty(FROMID, fromid);
	}

	public List findByToid(Object toid) {
		return findByProperty(TOID, toid);
	}

	public List findByCycle(Object cycle) {
		return findByProperty(CYCLE, cycle);
	}

	public List findByWay(Object way) {
		return findByProperty(WAY, way);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findBySent(Object sent) {
		return findByProperty(SENT, sent);
	}

	public List findAll() {
		log.debug("finding all Remind instances");
		try {
			String queryString = "from Remind";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Remind merge(Remind detachedInstance) {
		log.debug("merging Remind instance");
		try {
			Remind result = (Remind) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Remind instance) {
		log.debug("attaching dirty Remind instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Remind instance) {
		log.debug("attaching clean Remind instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RemindDao getFromApplicationContext(ApplicationContext ctx) {
		return (RemindDao) ctx.getBean("RemindDAO");
	}
}