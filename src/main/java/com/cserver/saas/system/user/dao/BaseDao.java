package com.cserver.saas.system.user.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T>{
	public abstract void addObject(T entity) throws Exception;
	public abstract void updateObject(T entity) throws Exception;
	public abstract T getObject(String id);
	public abstract void deleteObject(String id) throws Exception;
	public abstract List<T> searchList(Map<String, Object> map);
	public long searchCounts(Map<String, Object> map) throws Exception;
}
