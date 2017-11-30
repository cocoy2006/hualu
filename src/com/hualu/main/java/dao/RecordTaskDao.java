package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.RecordTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * RecordTask entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.RecordTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RecordTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(RecordTaskDao.class);
	// property constants
	public static final String USERID = "userid";
	public static final String CURRENTID = "currentid";
	public static final String IDS = "ids";
	public static final String PIECEIDS = "pieceids";
	public static final String ECGWAVE1IDS = "ecgwave1ids";
	public static final String HEARTRATE = "heartrate";
	public static final String HEARTRATERESULT = "heartrateresult";
	public static final String HEARTRATEIDS = "heartrateids";
	public static final String BREATHRATE = "breathrate";
	public static final String BREATHRATERESULT = "breathrateresult";
	public static final String BREATHRATEIDS = "breathrateids";
	public static final String SPO2VALUE = "spo2value";
	public static final String SPO2VALUERESULT = "spo2valueresult";
	public static final String SPO2VALUEIDS = "spo2valueids";
	public static final String PLUSERATE = "pluserate";
	public static final String PLUSERATERESULT = "pluserateresult";
	public static final String PLUSERATEIDS = "pluserateids";
	public static final String BPHIGH = "bphigh";
	public static final String BPLOW = "bplow";
	public static final String BPAVG = "bpavg";
	public static final String BPRESULT = "bpresult";
	public static final String BPIDS = "bpids";
	public static final String BPRATE = "bprate";
	public static final String BPRATERESULT = "bprateresult";
	public static final String BPRATEIDS = "bprateids";
	public static final String TEMP1 = "temp1";
	public static final String TEMP1RESULT = "temp1result";
	public static final String TEMP1IDS = "temp1ids";
	public static final String HOUR = "hour";
	public static final String STATUS = "status";
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public int save(RecordTask transientInstance) {
		log.debug("saving RecordTask instance");
		try {
			log.debug("save successful");
			return (Integer) getHibernateTemplate().save(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RecordTask persistentInstance) {
		log.debug("deleting RecordTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RecordTask findById(java.lang.Integer id) {
		log.debug("getting RecordTask instance with id: " + id);
		try {
			RecordTask instance = (RecordTask) getHibernateTemplate().get(
					"com.hualu.main.java.entity.RecordTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RecordTask> findByExample(RecordTask instance) {
		log.debug("finding RecordTask instance by example");
		try {
			List<RecordTask> results = (List<RecordTask>) getHibernateTemplate()
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
		log.debug("finding RecordTask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RecordTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RecordTask> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List<RecordTask> findByCurrentid(Object currentid) {
		return findByProperty(CURRENTID, currentid);
	}

	public List<RecordTask> findByIds(Object ids) {
		return findByProperty(IDS, ids);
	}

	public List<RecordTask> findByPieceids(Object pieceids) {
		return findByProperty(PIECEIDS, pieceids);
	}

	public List<RecordTask> findByEcgwave1ids(Object ecgwave1ids) {
		return findByProperty(ECGWAVE1IDS, ecgwave1ids);
	}

	public List<RecordTask> findByHeartrate(Object heartrate) {
		return findByProperty(HEARTRATE, heartrate);
	}

	public List<RecordTask> findByHeartrateresult(Object heartrateresult) {
		return findByProperty(HEARTRATERESULT, heartrateresult);
	}

	public List<RecordTask> findByHeartrateids(Object heartrateids) {
		return findByProperty(HEARTRATEIDS, heartrateids);
	}

	public List<RecordTask> findByBreathrate(Object breathrate) {
		return findByProperty(BREATHRATE, breathrate);
	}

	public List<RecordTask> findByBreathrateresult(Object breathrateresult) {
		return findByProperty(BREATHRATERESULT, breathrateresult);
	}

	public List<RecordTask> findByBreathrateids(Object breathrateids) {
		return findByProperty(BREATHRATEIDS, breathrateids);
	}

	public List<RecordTask> findBySpo2value(Object spo2value) {
		return findByProperty(SPO2VALUE, spo2value);
	}

	public List<RecordTask> findBySpo2valueresult(Object spo2valueresult) {
		return findByProperty(SPO2VALUERESULT, spo2valueresult);
	}

	public List<RecordTask> findBySpo2valueids(Object spo2valueids) {
		return findByProperty(SPO2VALUEIDS, spo2valueids);
	}

	public List<RecordTask> findByPluserate(Object pluserate) {
		return findByProperty(PLUSERATE, pluserate);
	}

	public List<RecordTask> findByPluserateresult(Object pluserateresult) {
		return findByProperty(PLUSERATERESULT, pluserateresult);
	}

	public List<RecordTask> findByPluserateids(Object pluserateids) {
		return findByProperty(PLUSERATEIDS, pluserateids);
	}

	public List<RecordTask> findByBphigh(Object bphigh) {
		return findByProperty(BPHIGH, bphigh);
	}

	public List<RecordTask> findByBplow(Object bplow) {
		return findByProperty(BPLOW, bplow);
	}

	public List<RecordTask> findByBpavg(Object bpavg) {
		return findByProperty(BPAVG, bpavg);
	}

	public List<RecordTask> findByBpresult(Object bpresult) {
		return findByProperty(BPRESULT, bpresult);
	}

	public List<RecordTask> findByBpids(Object bpids) {
		return findByProperty(BPIDS, bpids);
	}

	public List<RecordTask> findByBprate(Object bprate) {
		return findByProperty(BPRATE, bprate);
	}

	public List<RecordTask> findByBprateresult(Object bprateresult) {
		return findByProperty(BPRATERESULT, bprateresult);
	}

	public List<RecordTask> findByBprateids(Object bprateids) {
		return findByProperty(BPRATEIDS, bprateids);
	}

	public List<RecordTask> findByTemp1(Object temp1) {
		return findByProperty(TEMP1, temp1);
	}

	public List<RecordTask> findByTemp1result(Object temp1result) {
		return findByProperty(TEMP1RESULT, temp1result);
	}

	public List<RecordTask> findByTemp1ids(Object temp1ids) {
		return findByProperty(TEMP1IDS, temp1ids);
	}

	public List<RecordTask> findByHour(Object hour) {
		return findByProperty(HOUR, hour);
	}

	public List<RecordTask> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<RecordTask> findByIsdelete(Object isdelete) {
		return findByProperty(ISDELETE, isdelete);
	}

	public List findAll() {
		log.debug("finding all RecordTask instances");
		try {
			String queryString = "from RecordTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RecordTask merge(RecordTask detachedInstance) {
		log.debug("merging RecordTask instance");
		try {
			RecordTask result = (RecordTask) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RecordTask instance) {
		log.debug("attaching dirty RecordTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RecordTask instance) {
		log.debug("attaching clean RecordTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RecordTaskDao getFromApplicationContext(ApplicationContext ctx) {
		return (RecordTaskDao) ctx.getBean("RecordTaskDAO");
	}
}