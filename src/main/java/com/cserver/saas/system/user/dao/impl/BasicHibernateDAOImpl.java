package com.cserver.saas.system.user.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cserver.saas.system.user.dao.BasicHibernateDAO;


public class BasicHibernateDAOImpl<T, PK extends Serializable>
  extends HibernateDaoSupport
  implements BasicHibernateDAO<T, PK>
{
  private Class<T> entityClass;
  
  public BasicHibernateDAOImpl()
  {
    this.entityClass = null;
    Class c = getClass();
    Type t = c.getGenericSuperclass();
    if ((t instanceof ParameterizedType))
    {
      Type[] p = ((ParameterizedType)t).getActualTypeArguments();
      this.entityClass = ((Class)p[0]);
    }
  }
  
  public Class<T> getEntityClass()
  {
    return this.entityClass;
  }
  
  public void add(T entity)
  {
    super.getHibernateTemplate().save(entity);
  }
  
  public void update(T entity)
  {
    super.getHibernateTemplate().update(entity);
  }
  
  public void merge(T entity)
  {
    super.getHibernateTemplate().merge(entity);
  }
  
  public void delete(T entity)
  {
    super.getHibernateTemplate().delete(entity);
  }
  
  public void deleteByKey(PK id)
  {
    delete(getHibernateTemplate().load(this.entityClass, id));
  }
  
  public List<T> getAllOb()
  {
    return super.getHibernateTemplate().loadAll(this.entityClass);
  }
  
  public T getOb(PK id)
  {
    return (T)super.getHibernateTemplate().get(this.entityClass, id);
  }
  
  public void saveOrUpdate(T entity)
  {
    super.getHibernateTemplate().saveOrUpdate(entity);
  }
  
  public void saveOrUpdateAll(Collection<T> entities)
  {
    super.getHibernateTemplate().saveOrUpdateAll(entities);
  }
  
  public void deleteAll(Collection<T> entities)
  {
    super.getHibernateTemplate().deleteAll(entities);
  }
}

