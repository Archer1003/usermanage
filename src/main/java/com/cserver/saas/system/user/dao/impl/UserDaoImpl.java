package com.cserver.saas.system.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cserver.saas.system.user.dao.UserDao;
import com.cserver.saas.system.user.entity.User;
import com.cserver.saas.system.user.util.PageUtil;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<User> searchList(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer("from User t where 1 = 1 ");
		getWhere(map, sql);
		sql.append(" order by t.updateTime desc");
		final String hql = sql.toString();
		System.out.println(hql);
		final int startRow = PageUtil.getStartRow(map);
		final int pageSize = PageUtil.getPageSize(map);
		List<User> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				try {
					Query query = session.createQuery(hql);
					if (pageSize != 0 && startRow != -1) {
						query.setFirstResult(startRow);
						query.setMaxResults(pageSize);
					}
					List<User> listTemp = query.list();
					getSession().clear();
					return listTemp;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		return list;
	}

	@Override
	public long searchCounts(Map<String, Object> map) throws Exception {
		StringBuffer sqlCount = new StringBuffer("select count(*) from User t where  1 = 1 ");
		getWhere(map, sqlCount);
		final String hql = sqlCount.toString();
		final List<Object> list = new ArrayList<Object>();
		List list1 =getHibernateTemplate().executeFind(
				new HibernateCallback() {
					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						try {
							Query query = session.createQuery(hql);
							List<Object> listTemp = query.list();
							list.addAll(listTemp);
							getSession().clear();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
		if(list.size() > 0){
			return Long.parseLong(list.get(0)+"");
		}
		return -1;
	}
	
	public StringBuffer getWhere(Map<String, Object> map, StringBuffer sql) {
		if (null != map) {
			if (null != map.get("name") && !"".equals(map.get("name"))) {
				sql.append(" and t.name like '%" + map.get("name") + "%'");
			}
			if (null != map.get("sameName") && !"".equals(map.get("sameName"))) {
				sql.append(" and t.name = '" + map.get("sameName") + "'");
			}
			if(null != map.get("notOwnId") && !"".equals(map.get("notOwnId"))) {
				sql.append(" and t.id != '" + map.get("notOwnId") + "'");
			}
		}
		return sql;
	}
}
