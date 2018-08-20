package com.someSite.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {
    @Autowired
    SessionFactory sessionFactory;

    public void persist(Object entity){
        getSession().persist(entity);
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void delete(Object entity){
        getSession().delete(entity);
    }

    public  void saveOrUpdateIfExist(Object entity){
        getSession().saveOrUpdate(entity);
    }
}
