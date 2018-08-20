package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ValidationTypeIntDao;
import com.someSite.entity.firstApplication.ValidationType;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("ValidationTypeIntDao")
public class ValidationTypeDao extends AbstractDao implements ValidationTypeIntDao {

    public void save(ValidationType object) {
        persist(object);
    }

    public List<ValidationType> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationType.class);
        return (List<ValidationType>)criteria.list();
    }

    public ValidationType findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationType.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationType)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationType   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(ValidationType object){
        saveOrUpdateIfExist(object);
    }
}
