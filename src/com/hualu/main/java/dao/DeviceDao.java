package com.hualu.main.java.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.Device;

/**
 * A data access object (DAO) providing persistence and search support for
 * Device entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.Device
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DeviceDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(Device.class);
	// property constants
	public static final String DSN = "dsn";
	public static final String DSTR = "dstr";
	public static final String G3NUM = "g3num";
	public static final String DSTATUS = "dstatus";
	public static final String PACKID = "packid";
	public static final String ADMIN_NAME = "adminName";

	public int save(Device transientInstance) {
		log.debug("saving Device instance");
		try {
			log.debug("save successful");
			return (Integer) getHibernateTemplate().save(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Device persistentInstance) {
		log.debug("deleting Device instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Device findById(java.lang.Integer id) {
		log.debug("getting Device instance with id: " + id);
		try {
			Device instance = (Device) getHibernateTemplate().get(
					"com.hualu.main.java.entity.Device", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Device instance) {
		log.debug("finding Device instance by example");
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
		log.debug("finding Device instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Device as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDsn(Object dsn) {
		return findByProperty(DSN, dsn);
	}

	public List findByDstr(Object dstr) {
		return findByProperty(DSTR, dstr);
	}

	public List findByG3num(Object g3num) {
		return findByProperty(G3NUM, g3num);
	}

	public List findByDstatus(Object dstatus) {
		return findByProperty(DSTATUS, dstatus);
	}

	public List findByPackid(Object packid) {
		return findByProperty(PACKID, packid);
	}

	public List findByAdminName(Object adminName) {
		return findByProperty(ADMIN_NAME, adminName);
	}

	public List<Device> findAll() {
		log.debug("finding all Device instances");
		try {
			String queryString = "from Device";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}