package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ValidationIntDao;
import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ValidationIntDao")
public class ValidationDao extends AbstractDao implements ValidationIntDao {

    public void save(Validation object) {
        persist(object);
    }

    public List<Validation> findAll() {
        Criteria criteria = getSession().createCriteria(Validation.class);
        return (List<Validation>)criteria.list();
    }

    public Validation findById(int id) {
        Criteria criteria = getSession().createCriteria(Validation.class);
        criteria.add(Restrictions.eq("id", id));
        return (Validation)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  Validation   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(Validation object){
        saveOrUpdateIfExist(object);
    }
}
