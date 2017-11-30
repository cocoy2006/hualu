package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.RecordDaily;

/**
 * A data access object (DAO) providing persistence and search support for
 * RecordDaily entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.RecordDaily
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RecordDailyDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(RecordDailyDao.class);
	// property constants
	public static final String USERID = "userid";
	public static final String IDS = "ids";
	public static final String HOURS = "hours";
	public static final String HEARTRATE = "heartrate";
	public static final String HEARTRATERESULT = "heartrateresult";
	public static final String BREATHRATE = "breathrate";
	public static final String BREATHRATERESULT = "breathrateresult";
	public static final String SPO2VALUE = "spo2value";
	public static final String SPO2VALUERESULT = "spo2valueresult";
	public static final String PLUSERATE = "pluserate";
	public static final String PLUSERATERESULT = "pluserateresult";
	public static final String BPHIGH = "bphigh";
	public static final String BPLOW = "bplow";
	public static final String BPAVG = "bpavg";
	public static final String BPRESULT = "bpresult";
	public static final String BPRATE = "bprate";
	public static final String BPRATERESULT = "bprateresult";
	public static final String TEMP1 = "temp1";
	public static final String TEMP1RESULT = "temp1result";

	protected void initDao() {
		// do nothing
	}

	public int save(RecordDaily transientInstance) {
		log.debug("saving RecordDaily instance");
		try {
			log.debug("save successful");
			return (Integer) getHibernateTemplate().save(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RecordDaily persistentInstance) {
		log.debug("deleting RecordDaily instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RecordDaily findById(java.lang.Integer id) {
		log.debug("getting RecordDaily instance with id: " + id);
		try {
			RecordDaily instance = (RecordDaily) getHibernateTemplate().get(
					"com.hualu.main.java.entity.RecordDaily", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RecordDaily> findByExample(RecordDaily instance) {
		log.debug("finding RecordDaily instance by example");
		try {
			List<RecordDaily> results = (List<RecordDaily>) getHibernateTemplate()
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
		log.debug("finding RecordDaily instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RecordDaily as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RecordDaily> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List<RecordDaily> findByIds(Object ids) {
		return findByProperty(IDS, ids);
	}

	public List<RecordDaily> findByHours(Object hours) {
		return findByProperty(HOURS, hours);
	}

	public List<RecordDaily> findByHeartrate(Object heartrate) {
		return findByProperty(HEARTRATE, heartrate);
	}

	public List<RecordDaily> findByHeartrateresult(Object heartrateresult) {
		return findByProperty(HEARTRATERESULT, heartrateresult);
	}

	public List<RecordDaily> findByBreathrate(Object breathrate) {
		return findByProperty(BREATHRATE, breathrate);
	}

	public List<RecordDaily> findByBreathrateresult(Object breathrateresult) {
		return findByProperty(BREATHRATERESULT, breathrateresult);
	}

	public List<RecordDaily> findBySpo2value(Object spo2value) {
		return findByProperty(SPO2VALUE, spo2value);
	}

	public List<RecordDaily> findBySpo2valueresult(Object spo2valueresult) {
		return findByProperty(SPO2VALUERESULT, spo2valueresult);
	}

	public List<RecordDaily> findByPluserate(Object pluserate) {
		return findByProperty(PLUSERATE, pluserate);
	}

	public List<RecordDaily> findByPluserateresult(Object pluserateresult) {
		return findByProperty(PLUSERATERESULT, pluserateresult);
	}

	public List<RecordDaily> findByBphigh(Object bphigh) {
		return findByProperty(BPHIGH, bphigh);
	}

	public List<RecordDaily> findByBplow(Object bplow) {
		return findByProperty(BPLOW, bplow);
	}

	public List<RecordDaily> findByBpavg(Object bpavg) {
		return findByProperty(BPAVG, bpavg);
	}

	public List<RecordDaily> findByBpresult(Object bpresult) {
		return findByProperty(BPRESULT, bpresult);
	}

	public List<RecordDaily> findByBprate(Object bprate) {
		return findByProperty(BPRATE, bprate);
	}

	public List<RecordDaily> findByBprateresult(Object bprateresult) {
		return findByProperty(BPRATERESULT, bprateresult);
	}

	public List<RecordDaily> findByTemp1(Object temp1) {
		return findByProperty(TEMP1, temp1);
	}

	public List<RecordDaily> findByTemp1result(Object temp1result) {
		return findByProperty(TEMP1RESULT, temp1result);
	}

	public List findAll() {
		log.debug("finding all RecordDaily instances");
		try {
			String queryString = "from RecordDaily";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RecordDaily merge(RecordDaily detachedInstance) {
		log.debug("merging RecordDaily instance");
		try {
			RecordDaily result = (RecordDaily) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RecordDaily instance) {
		log.debug("attaching dirty RecordDaily instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RecordDaily instance) {
		log.debug("attaching clean RecordDaily instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RecordDailyDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (RecordDailyDao) ctx.getBean("RecordDailyDAO");
	}
}