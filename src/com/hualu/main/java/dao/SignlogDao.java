package com.hualu.main.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hualu.main.java.entity.Signlog;
import com.hualu.main.java.util.jdbc.MySQL;

/**
 * A data access object (DAO) providing persistence and search support for
 * Signlog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hualu.main.java.entity.Signlog
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SignlogDao extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(SignlogDao.class);
	// property constants
	public static final String OPERATORID = "operatorid";
	public static final String ONLINETIME = "onlinetime";
	public static final String IP = "ip";

	protected void initDao() {
		// do nothing
	}

	public void save(Signlog transientInstance) {
		log.debug("saving Signlog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Signlog persistentInstance) {
		log.debug("deleting Signlog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Signlog findById(java.lang.Integer id) {
		log.debug("getting Signlog instance with id: " + id);
		try {
			Signlog instance = (Signlog) getHibernateTemplate().get(
					"com.hualu.main.java.entity.Signlog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Signlog> findByExample(Signlog instance) {
		log.debug("finding Signlog instance by example");
		try {
			List<Signlog> results = (List<Signlog>) getHibernateTemplate()
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
		log.debug("finding Signlog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Signlog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Signlog> findByOperatorid(Object operatorid) {
		return findByProperty(OPERATORID, operatorid);
	}

	public List<Signlog> findByOnlinetime(Object onlinetime) {
		return findByProperty(ONLINETIME, onlinetime);
	}

	public List<Signlog> findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findAll() {
		log.debug("finding all Signlog instances");
		try {
			String queryString = "from Signlog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public static Signlog findLast(int operatorid) {
		String sql = "SELECT * FROM signlog WHERE operatorid=" + operatorid;
		ResultSet rs = null;
		try {
			rs = MySQL.executeQuery(sql);
			if(rs.last()) {
				Signlog signlog = new Signlog();
				signlog.setId(rs.getInt("id"));
				signlog.setOperatorid(rs.getInt("operatorid"));
				signlog.setSignintime(rs.getTimestamp("signintime"));
				signlog.setSignouttime(rs.getTimestamp("signouttime"));
				signlog.setOnlinetime(rs.getInt("onlinetime"));
				signlog.setIp(rs.getString("ip"));
				return signlog;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}
		return null;
	}
	
	public static int updateSignouttime(int id, Timestamp signouttime, long onlinetime) {
		String sql = "UPDATE signlog SET signouttime='" + signouttime + 
			"',  onlinetime=" + onlinetime + 
			" WHERE id=" + id;
		return MySQL.executeUpdate(sql);
	}

}