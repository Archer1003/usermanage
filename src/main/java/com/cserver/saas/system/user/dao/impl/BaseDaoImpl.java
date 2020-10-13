package com.cserver.saas.system.user.dao.impl;

import java.util.List;
import java.util.Map;

import com.cserver.saas.system.user.dao.BaseDao;

public class BaseDaoImpl<T> extends BasicHibernateDAOImpl<T, String> implements BaseDao<T> {

	@Override
	public void addObject(T entity) throws Exception {
		saveOrUpdate(entity);
	}

	@Override
	public void updateObject(T entity) throws Exception {
		update(entity);
	}

	@Override
	public T getObject(String id) {
		T t = getOb(id);
		getSession().clear();
		return t;
	}

	@Override
	public void deleteObject(String id) throws Exception {
		deleteByKey(id);
	}
	
	@Override
	public List<T> searchList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long searchCounts(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
