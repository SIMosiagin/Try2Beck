package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ValidationListIntDao;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ValidationListIntDao")
public class ValidationListDao extends AbstractDao implements ValidationListIntDao {

    public void save(ValidationList object) {
        persist(object);
    }

    public List<ValidationList> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationList.class);
        return (List<ValidationList>)criteria.list();
    }

    public ValidationList findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationList.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationList)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationList   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(ValidationList object){
        saveOrUpdateIfExist(object);
    }
}
