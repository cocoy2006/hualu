package com.hualu.main.java.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.DevicePackage;

/**
 * A data access object (DAO) providing persistence and search support for
 * DevicePackage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.DevicePackage
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DevicePackageDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(DevicePackage.class);
	// property constants
	public static final String DMODEL = "dmodel";
	public static final String DNAME = "dname";
	public static final String DIP = "dip";
	public static final String DINFO = "dinfo";
	public static final String DPIC = "dpic";
	public static final String COMID = "comid";

	public void delete(DevicePackage persistentInstance) {
		log.debug("deleting DevicePackage instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DevicePackage findById(java.lang.Integer id) {
		log.debug("getting DevicePackage instance with id: " + id);
		try {
			DevicePackage instance = (DevicePackage) getHibernateTemplate()
					.get("com.hualu.main.java.entity.DevicePackage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DevicePackage instance) {
		log.debug("finding DevicePackage instance by example");
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
		log.debug("finding DevicePackage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DevicePackage as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDmodel(Object dmodel) {
		return findByProperty(DMODEL, dmodel);
	}

	public List findByDname(Object dname) {
		return findByProperty(DNAME, dname);
	}

	public List findByDip(Object dip) {
		return findByProperty(DIP, dip);
	}

	public List findByDinfo(Object dinfo) {
		return findByProperty(DINFO, dinfo);
	}

	public List findByDpic(Object dpic) {
		return findByProperty(DPIC, dpic);
	}

	public List findByComid(Object comid) {
		return findByProperty(COMID, comid);
	}

	public List<DevicePackage> findAll() {
		log.debug("finding all DevicePackage instances");
		try {
			String queryString = "from DevicePackage";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}