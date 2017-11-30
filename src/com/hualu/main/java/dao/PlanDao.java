package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.Plan;

/**
 * A data access object (DAO) providing persistence and search support for Plan
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hualu.main.java.entity.Plan
 * @author MyEclipse Persistence Tools
 */
@Repository
public class PlanDao extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(PlanDao.class);
	// property constants
	public static final String FROMID = "fromid";
	public static final String TOID = "toid";
	public static final String TYPE = "type";
	public static final String PERIOD = "period";
	public static final String CYCLE = "cycle";
	public static final String WAY = "way";
	public static final String CONTENT = "content";
	public static final String STATUS = "status";
	public static final String SENT = "sent";

	protected void initDao() {
		// do nothing
	}

	public void save(Plan transientInstance) {
		log.debug("saving Plan instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Plan persistentInstance) {
		log.debug("deleting Plan instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Plan findById(java.lang.Integer id) {
		log.debug("getting Plan instance with id: " + id);
		try {
			Plan instance = (Plan) getHibernateTemplate().get(
					"com.hualu.main.java.entity.Plan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Plan> findByExample(Plan instance) {
		log.debug("finding Plan instance by example");
		try {
			List<Plan> results = (List<Plan>) getHibernateTemplate()
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
		log.debug("finding Plan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Plan as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Plan> findByFromid(Object fromid) {
		return findByProperty(FROMID, fromid);
	}

	public List<Plan> findByToid(Object toid) {
		return findByProperty(TOID, toid);
	}

	public List<Plan> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Plan> findByPeriod(Object period) {
		return findByProperty(PERIOD, period);
	}

	public List<Plan> findByCycle(Object cycle) {
		return findByProperty(CYCLE, cycle);
	}

	public List<Plan> findByWay(Object way) {
		return findByProperty(WAY, way);
	}

	public List<Plan> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Plan> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Plan> findBySent(Object sent) {
		return findByProperty(SENT, sent);
	}

	public List findAll() {
		log.debug("finding all Plan instances");
		try {
			String queryString = "from Plan";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Plan merge(Plan detachedInstance) {
		log.debug("merging Plan instance");
		try {
			Plan result = (Plan) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Plan instance) {
		log.debug("attaching dirty Plan instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Plan instance) {
		log.debug("attaching clean Plan instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PlanDao getFromApplicationContext(ApplicationContext ctx) {
		return (PlanDao) ctx.getBean("PlanDAO");
	}
}