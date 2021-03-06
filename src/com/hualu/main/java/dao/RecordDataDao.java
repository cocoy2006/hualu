package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.RecordData;

/**
 * A data access object (DAO) providing persistence and search support for
 * RecordData entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.RecordData
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RecordDataDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(RecordDataDao.class);
	// property constants
	public static final String USERID = "userid";
	public static final String DSN = "dsn";
	public static final String DSTR = "dstr";
	public static final String RFILENAME = "rfilename";
	public static final String ECGWAVE1 = "ecgwave1";
	public static final String BREATHWAVE = "breathwave";
	public static final String HEARTRATE = "heartrate";
	public static final String BREATHRATE = "breathrate";
	public static final String SPO2WAVE = "spo2wave";
	public static final String SPO2VALUE = "spo2value";
	public static final String PLUSERATE = "pluserate";
	public static final String BPHIGH = "bphigh";
	public static final String BPLOW = "bplow";
	public static final String BPAVG = "bpavg";
	public static final String BPRATE = "bprate";
	public static final String TEMP1 = "temp1";
	public static final String TEMP2 = "temp2";
	public static final String MEMO = "memo";
	public static final String IS_CHECKED = "isChecked";

	protected void initDao() {
		// do nothing
	}

	public void save(RecordData transientInstance) {
		log.debug("saving RecordData instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RecordData persistentInstance) {
		log.debug("deleting RecordData instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RecordData findById(java.lang.Integer id) {
		log.debug("getting RecordData instance with id: " + id);
		try {
			RecordData instance = (RecordData) getHibernateTemplate().get(
					"com.hualu.main.java.entity.RecordData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RecordData instance) {
		log.debug("finding RecordData instance by example");
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
		log.debug("finding RecordData instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RecordData as model where model."
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

	public List findByDsn(Object dsn) {
		return findByProperty(DSN, dsn);
	}

	public List findByDstr(Object dstr) {
		return findByProperty(DSTR, dstr);
	}

	public List findByRfilename(Object rfilename) {
		return findByProperty(RFILENAME, rfilename);
	}

	public List findByEcgwave1(Object ecgwave1) {
		return findByProperty(ECGWAVE1, ecgwave1);
	}

	public List findByBreathwave(Object breathwave) {
		return findByProperty(BREATHWAVE, breathwave);
	}

	public List findByHeartrate(Object heartrate) {
		return findByProperty(HEARTRATE, heartrate);
	}

	public List findByBreathrate(Object breathrate) {
		return findByProperty(BREATHRATE, breathrate);
	}

	public List findBySpo2wave(Object spo2wave) {
		return findByProperty(SPO2WAVE, spo2wave);
	}

	public List findBySpo2value(Object spo2value) {
		return findByProperty(SPO2VALUE, spo2value);
	}

	public List findByPluserate(Object pluserate) {
		return findByProperty(PLUSERATE, pluserate);
	}

	public List findByBphigh(Object bphigh) {
		return findByProperty(BPHIGH, bphigh);
	}

	public List findByBplow(Object bplow) {
		return findByProperty(BPLOW, bplow);
	}

	public List findByBpavg(Object bpavg) {
		return findByProperty(BPAVG, bpavg);
	}

	public List findByBprate(Object bprate) {
		return findByProperty(BPRATE, bprate);
	}

	public List findByTemp1(Object temp1) {
		return findByProperty(TEMP1, temp1);
	}

	public List findByTemp2(Object temp2) {
		return findByProperty(TEMP2, temp2);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByIsChecked(Object isChecked) {
		return findByProperty(IS_CHECKED, isChecked);
	}

	public List findAll() {
		log.debug("finding all RecordData instances");
		try {
			String queryString = "from RecordData";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RecordData merge(RecordData detachedInstance) {
		log.debug("merging RecordData instance");
		try {
			RecordData result = (RecordData) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RecordData instance) {
		log.debug("attaching dirty RecordData instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RecordData instance) {
		log.debug("attaching clean RecordData instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RecordDataDao getFromApplicationContext(ApplicationContext ctx) {
		return (RecordDataDao) ctx.getBean("RecordDataDAO");
	}
}