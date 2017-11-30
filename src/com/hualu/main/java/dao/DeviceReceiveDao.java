package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.DeviceReceive;

/**
 * A data access object (DAO) providing persistence and search support for
 * DeviceReceive entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.DeviceReceive
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DeviceReceiveDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(DeviceReceiveDao.class);
	// property constants
	public static final String DEVICEID = "deviceid";
	public static final String USERID = "userid";
	public static final String NURSEID = "nurseid";
	public static final String ADDRESS = "address";
	public static final String MEMO = "memo";
	public static final String STATUS = "status";
	public static final String DSTR = "dstr";

	protected void initDao() {
		// do nothing
	}

	public void save(DeviceReceive transientInstance) {
		log.debug("saving DeviceReceive instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DeviceReceive persistentInstance) {
		log.debug("deleting DeviceReceive instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DeviceReceive findById(java.lang.Integer id) {
		log.debug("getting DeviceReceive instance with id: " + id);
		try {
			DeviceReceive instance = (DeviceReceive) getHibernateTemplate()
					.get("com.hualu.main.java.entity.DeviceReceive", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DeviceReceive instance) {
		log.debug("finding DeviceReceive instance by example");
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
		log.debug("finding DeviceReceive instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DeviceReceive as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public DeviceReceive findByDeviceid(Object deviceid) {
		List<DeviceReceive> drs = findByProperty(DEVICEID, deviceid);
		if(drs != null && drs.size() > 0) {
			return drs.get(0);
		}
		return null;
	}

	public List findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findByNurseid(Object nurseid) {
		return findByProperty(NURSEID, nurseid);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByDstr(Object dstr) {
		return findByProperty(DSTR, dstr);
	}

	public List findAll() {
		log.debug("finding all DeviceReceive instances");
		try {
			String queryString = "from DeviceReceive";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DeviceReceive merge(DeviceReceive detachedInstance) {
		log.debug("merging DeviceReceive instance");
		try {
			DeviceReceive result = (DeviceReceive) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DeviceReceive instance) {
		log.debug("attaching dirty DeviceReceive instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DeviceReceive instance) {
		log.debug("attaching clean DeviceReceive instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeviceReceiveDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (DeviceReceiveDao) ctx.getBean("DeviceReceiveDAO");
	}
}