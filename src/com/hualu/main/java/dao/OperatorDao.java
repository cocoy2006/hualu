package com.hualu.main.java.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.jdbc.MySQL;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operator entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.Operator
 * @author MyEclipse Persistence Tools
 */
@Repository
public class OperatorDao extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(OperatorDao.class);
	// property constants
	public static final String LOGINNAME = "loginname";
	public static final String NAME = "name";
	public static final String GENDA = "genda";
	public static final String UPHOTOURL = "uphotourl";
	public static final String HOSPITAL = "hospital";
	public static final String DEPART = "depart";
	public static final String TITLE = "title";
	public static final String STATUS = "status";
	public static final String ISONLINE = "isonline";
	public static final String OPENTIME = "opentime";
	public static final String MOBILE = "mobile";
	public static final String MEMO = "memo";
	public static final String WECHAT_ID = "wechatId";
	public static final String WECHAT_NAME = "wechatName";
	public static final String ROLE = "role";
	public static final String PASSWD = "passwd";

	protected void initDao() {
		// do nothing
	}

	public void save(Operator transientInstance) {
		log.debug("saving Operator instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Operator persistentInstance) {
		log.debug("deleting Operator instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Operator findById(java.lang.Integer id) {
		log.debug("getting Operator instance with id: " + id);
		try {
			Operator instance = (Operator) getHibernateTemplate().get(
					"com.hualu.main.java.entity.Operator", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Operator instance) {
		log.debug("finding Operator instance by example");
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
		log.debug("finding Operator instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Operator as model where model."
					+ propertyName + "= ? and status != " + Status.OperatorStatus.REMOVED.getInt();
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByGenda(Object genda) {
		return findByProperty(GENDA, genda);
	}

	public List findByUphotourl(Object uphotourl) {
		return findByProperty(UPHOTOURL, uphotourl);
	}

	public List findByHospital(Object hospital) {
		return findByProperty(HOSPITAL, hospital);
	}

	public List findByDepart(Object depart) {
		return findByProperty(DEPART, depart);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByIsonline(Object isonline) {
		return findByProperty(ISONLINE, isonline);
	}

	public List findByOpentime(Object opentime) {
		return findByProperty(OPENTIME, opentime);
	}

	public List findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByWechatId(Object wechatId) {
		return findByProperty(WECHAT_ID, wechatId);
	}

	public List findByWechatName(Object wechatName) {
		return findByProperty(WECHAT_NAME, wechatName);
	}

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	public List findAll() {
		log.debug("finding all Operator instances");
		try {
			String queryString = "from Operator where status != " + Status.OperatorStatus.REMOVED.getInt();
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllIncludingRemoved() {
		log.debug("finding all Operator instances");
		try {
			String queryString = "from Operator";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public static int updateIsonline(int id, int isonline) {
		String sql = "UPDATE operator SET isonline=" + isonline + " WHERE id=" + id;
		return MySQL.executeUpdate(sql);
	}

}