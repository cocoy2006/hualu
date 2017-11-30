package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.ReportTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportTask entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.ReportTask
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ReportTaskDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(ReportTaskDao.class);
	// property constants
	public static final String USERID = "userid";
	public static final String DOCTORID = "doctorid";
	public static final String TYPE = "type";
	public static final String ADVICE = "advice";
	public static final String DAILYIDS = "dailyids";
	public static final String IDS = "ids";
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
	public static final String HOUR = "hour";
	public static final String STATUS = "status";
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public int save(ReportTask transientInstance) {
		log.debug("saving ReportTask instance");
		try {
			log.debug("save successful");
			return (Integer) getHibernateTemplate().save(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportTask persistentInstance) {
		log.debug("deleting ReportTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportTask findById(java.lang.Integer id) {
		log.debug("getting ReportTask instance with id: " + id);
		try {
			ReportTask instance = (ReportTask) getHibernateTemplate().get(
					"com.hualu.main.java.entity.ReportTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ReportTask> findByExample(ReportTask instance) {
		log.debug("finding ReportTask instance by example");
		try {
			List<ReportTask> results = (List<ReportTask>) getHibernateTemplate()
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
		log.debug("finding ReportTask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ReportTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ReportTask> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List<ReportTask> findByDoctorid(Object doctorid) {
		return findByProperty(DOCTORID, doctorid);
	}

	public List<ReportTask> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<ReportTask> findByAdvice(Object advice) {
		return findByProperty(ADVICE, advice);
	}

	public List<ReportTask> findByDailyids(Object dailyids) {
		return findByProperty(DAILYIDS, dailyids);
	}

	public List<ReportTask> findByIds(Object ids) {
		return findByProperty(IDS, ids);
	}

	public List<ReportTask> findByHeartrate(Object heartrate) {
		return findByProperty(HEARTRATE, heartrate);
	}

	public List<ReportTask> findByHeartrateresult(Object heartrateresult) {
		return findByProperty(HEARTRATERESULT, heartrateresult);
	}

	public List<ReportTask> findByBreathrate(Object breathrate) {
		return findByProperty(BREATHRATE, breathrate);
	}

	public List<ReportTask> findByBreathrateresult(Object breathrateresult) {
		return findByProperty(BREATHRATERESULT, breathrateresult);
	}

	public List<ReportTask> findBySpo2value(Object spo2value) {
		return findByProperty(SPO2VALUE, spo2value);
	}

	public List<ReportTask> findBySpo2valueresult(Object spo2valueresult) {
		return findByProperty(SPO2VALUERESULT, spo2valueresult);
	}

	public List<ReportTask> findByPluserate(Object pluserate) {
		return findByProperty(PLUSERATE, pluserate);
	}

	public List<ReportTask> findByPluserateresult(Object pluserateresult) {
		return findByProperty(PLUSERATERESULT, pluserateresult);
	}

	public List<ReportTask> findByBphigh(Object bphigh) {
		return findByProperty(BPHIGH, bphigh);
	}

	public List<ReportTask> findByBplow(Object bplow) {
		return findByProperty(BPLOW, bplow);
	}

	public List<ReportTask> findByBpavg(Object bpavg) {
		return findByProperty(BPAVG, bpavg);
	}

	public List<ReportTask> findByBpresult(Object bpresult) {
		return findByProperty(BPRESULT, bpresult);
	}

	public List<ReportTask> findByBprate(Object bprate) {
		return findByProperty(BPRATE, bprate);
	}

	public List<ReportTask> findByBprateresult(Object bprateresult) {
		return findByProperty(BPRATERESULT, bprateresult);
	}

	public List<ReportTask> findByTemp1(Object temp1) {
		return findByProperty(TEMP1, temp1);
	}

	public List<ReportTask> findByTemp1result(Object temp1result) {
		return findByProperty(TEMP1RESULT, temp1result);
	}

	public List<ReportTask> findByHour(Object hour) {
		return findByProperty(HOUR, hour);
	}

	public List<ReportTask> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<ReportTask> findByIsdelete(Object isdelete) {
		return findByProperty(ISDELETE, isdelete);
	}

	public List findAll() {
		log.debug("finding all ReportTask instances");
		try {
			String queryString = "from ReportTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportTask merge(ReportTask detachedInstance) {
		log.debug("merging ReportTask instance");
		try {
			ReportTask result = (ReportTask) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportTask instance) {
		log.debug("attaching dirty ReportTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportTask instance) {
		log.debug("attaching clean ReportTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportTaskDao getFromApplicationContext(ApplicationContext ctx) {
		return (ReportTaskDao) ctx.getBean("ReportTaskDAO");
	}
}