package com.cserver.saas.system.user.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
	public abstract void addObject(Object obj) throws Exception;
	public abstract void updateObject(Object obj) throws Exception;
	public abstract Object getObject(String id);
	public abstract List<Object> searchList(Map<String, Object> map);
	public long searchCounts(Map<String, Object> map) throws Exception;
	public abstract void deleteObject(String id) throws Exception;
}
