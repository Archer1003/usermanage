package com.cserver.saas.system.user.service.impl;

import com.cserver.saas.system.user.dao.BaseDao;
import com.cserver.saas.system.user.dao.UserDao;
import com.cserver.saas.system.user.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService{
	private UserDao userDao;	
	@Override
	public BaseDao getBaseDao() {
		return this.userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
