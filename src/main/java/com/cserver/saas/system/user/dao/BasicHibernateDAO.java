package com.cserver.saas.system.user.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract interface BasicHibernateDAO<T, PK extends Serializable>
{
  public abstract void add(T paramT);
  
  public abstract void update(T paramT);
  
  public abstract void merge(T paramT);
  
  public abstract void delete(T paramT);
  
  public abstract T getOb(PK paramPK);
  
  public abstract List<T> getAllOb();
  
  public abstract void saveOrUpdate(T paramT);
  
  public abstract void saveOrUpdateAll(Collection<T> paramCollection);
  
  public abstract void deleteByKey(PK paramPK);
  
  public abstract void deleteAll(Collection<T> paramCollection);
}
