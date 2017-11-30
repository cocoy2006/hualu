package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.Operationlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operationlog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.Operationlog
 * @author MyEclipse Persistence Tools
 */
@Repository
public class OperationlogDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(OperationlogDao.class);
	// property constants
	public static final String OPERATORID = "operatorid";
	public static final String OPERATORROLE = "operatorrole";
	public static final String OPERATIONTYPE = "operationtype";
	public static final String TARGETID = "targetid";
	public static final String TARGETTYPE = "targettype";

	protected void initDao() {
		// do nothing
	}

	public void save(Operationlog transientInstance) {
		log.debug("saving Operationlog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Operationlog persistentInstance) {
		log.debug("deleting Operationlog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Operationlog findById(java.lang.Integer id) {
		log.debug("getting Operationlog instance with id: " + id);
		try {
			Operationlog instance = (Operationlog) getHibernateTemplate().get(
					"com.hualu.main.java.entity.Operationlog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Operationlog> findByExample(Operationlog instance) {
		log.debug("finding Operationlog instance by example");
		try {
			List<Operationlog> results = (List<Operationlog>) getHibernateTemplate()
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
		log.debug("finding Operationlog instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Operationlog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Operationlog> findByOperatorid(Object operatorid) {
		return findByProperty(OPERATORID, operatorid);
	}

	public List<Operationlog> findByOperatorrole(Object operatorrole) {
		return findByProperty(OPERATORROLE, operatorrole);
	}

	public List<Operationlog> findByOperationtype(Object operationtype) {
		return findByProperty(OPERATIONTYPE, operationtype);
	}

	public List<Operationlog> findByTargetid(Object targetid) {
		return findByProperty(TARGETID, targetid);
	}

	public List<Operationlog> findByTargettype(Object targettype) {
		return findByProperty(TARGETTYPE, targettype);
	}

	public List findAll() {
		log.debug("finding all Operationlog instances");
		try {
			String queryString = "from Operationlog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Operationlog merge(Operationlog detachedInstance) {
		log.debug("merging Operationlog instance");
		try {
			Operationlog result = (Operationlog) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Operationlog instance) {
		log.debug("attaching dirty Operationlog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Operationlog instance) {
		log.debug("attaching clean Operationlog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OperationlogDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (OperationlogDao) ctx.getBean("OperationlogDAO");
	}
}