package com.cserver.saas.system.user.service.impl;

import java.util.List;
import java.util.Map;

import com.cserver.saas.system.user.dao.BaseDao;
import com.cserver.saas.system.user.service.BaseService;


public class BaseServiceImpl implements BaseService{
	
	@SuppressWarnings("rawtypes")
	public BaseDao getBaseDao() {
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void addObject(Object obj) throws Exception {
		getBaseDao().addObject(obj);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void updateObject(Object obj) throws Exception {
		getBaseDao().updateObject(obj);
	}

	@Override
	public Object getObject(String id) {
		return getBaseDao().getObject(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchList(Map<String, Object> map) {
		return getBaseDao().searchList(map);
	}
	@Override
	public void deleteObject(String id) throws Exception {
		getBaseDao().deleteObject(id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public long searchCounts(Map<String, Object> map) throws Exception {
		return getBaseDao().searchCounts(map);
	}
	
}
