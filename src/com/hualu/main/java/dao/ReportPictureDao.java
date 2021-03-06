package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.ReportPicture;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.ReportPicture
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ReportPictureDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(ReportPictureDao.class);
	// property constants
	public static final String USERID = "userid";
	public static final String TYPE = "type";
	public static final String URL = "url";

	protected void initDao() {
		// do nothing
	}

	public void save(ReportPicture transientInstance) {
		log.debug("saving ReportPicture instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportPicture persistentInstance) {
		log.debug("deleting ReportPicture instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportPicture findById(java.lang.Integer id) {
		log.debug("getting ReportPicture instance with id: " + id);
		try {
			ReportPicture instance = (ReportPicture) getHibernateTemplate()
					.get("com.hualu.main.java.entity.ReportPicture", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ReportPicture> findByExample(ReportPicture instance) {
		log.debug("finding ReportPicture instance by example");
		try {
			List<ReportPicture> results = (List<ReportPicture>) getHibernateTemplate()
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
		log.debug("finding ReportPicture instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportPicture as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ReportPicture> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List<ReportPicture> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<ReportPicture> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all ReportPicture instances");
		try {
			String queryString = "from ReportPicture";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportPicture merge(ReportPicture detachedInstance) {
		log.debug("merging ReportPicture instance");
		try {
			ReportPicture result = (ReportPicture) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportPicture instance) {
		log.debug("attaching dirty ReportPicture instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportPicture instance) {
		log.debug("attaching clean ReportPicture instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportPictureDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportPictureDao) ctx.getBean("ReportPictureDAO");
	}
}