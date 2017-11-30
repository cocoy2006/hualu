package com.hualu.main.java.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Status;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hualu.main.java.entity.User
 * @author MyEclipse Persistence Tools
 */
@Repository
public class UserDao extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	// property constants
	public static final String LOGINNAME = "loginname";
	public static final String PASSWD = "passwd";
	public static final String NAME = "name";
	public static final String DSTR = "dstr";
	public static final String GENDER = "gender";
	public static final String AGE = "age";
	public static final String PHOTOURL = "photourl";
	public static final String USERLEVEL = "userlevel";
	public static final String USERGROUP = "usergroup";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String HOMEADDRESS = "homeaddress";
	public static final String NURSEID = "nurseid";
	public static final String DOCTORID = "doctorid";
	public static final String SSNUM = "ssnum";
	public static final String COMPANYNAME = "companyname";
	public static final String COMPANYADDRESS = "companyaddress";
	public static final String WECHATID = "wechatid";
	public static final String WECHATNAME = "wechatname";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.hualu.main.java.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ? and status != " + Status.UserStatus.REMOVED.getInt();
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}
	
	public List findByDstr(Object dstr) {
		return findByProperty(DSTR, dstr);
	}

	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List findByPhotourl(Object photourl) {
		return findByProperty(PHOTOURL, photourl);
	}

	public List findByUserlevel(Object userlevel) {
		return findByProperty(USERLEVEL, userlevel);
	}

	public List findByUsergroup(Object usergroup) {
		return findByProperty(USERGROUP, usergroup);
	}
	
	public List<User> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}
	
	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByHomeaddress(Object homeaddress) {
		return findByProperty(HOMEADDRESS, homeaddress);
	}

	public List findByNurseid(Object nurseid) {
		return findByProperty(NURSEID, nurseid);
	}

	public List findByDoctorid(Object doctorid) {
		return findByProperty(DOCTORID, doctorid);
	}

	public List findBySsnum(Object ssnum) {
		return findByProperty(SSNUM, ssnum);
	}

	public List findByCompanyname(Object companyname) {
		return findByProperty(COMPANYNAME, companyname);
	}

	public List findByCompanyaddress(Object companyaddress) {
		return findByProperty(COMPANYADDRESS, companyaddress);
	}

	public List findByWechatid(Object wechatid) {
		return findByProperty(WECHATID, wechatid);
	}

	public List findByWechatname(Object wechatname) {
		return findByProperty(WECHATNAME, wechatname);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User where status != " + Status.UserStatus.REMOVED.getInt();
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDao getFromApplicationContext(ApplicationContext ctx) {
		return (UserDao) ctx.getBean("UserDAO");
	}
}