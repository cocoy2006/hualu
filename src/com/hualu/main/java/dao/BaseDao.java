package com.hualu.main.java.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDao<T> {

	private Class<T> clazz;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}
	
	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}
	
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(clazz);
	}
	
	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}
	
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	public T merge(T entity) {
		return getHibernateTemplate().merge(entity);
	}
	
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... params) {
		return getHibernateTemplate().find(hql, params);
	}
	
	public void initialize(Object entity) {
		getHibernateTemplate().initialize(entity);
	}
	
    @SuppressWarnings("unchecked")  
    public List<T> findByCriteria(DetachedCriteria dc) {  
        return getHibernateTemplate().findByCriteria(dc);  
    }
    
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final DetachedCriteria dc, final int firstResult, final int maxResults) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = dc.getExecutableCriteria(session);
				criteria.setFirstResult(firstResult).setMaxResults(maxResults);
				return criteria.list();
			}
		});
	}
}
