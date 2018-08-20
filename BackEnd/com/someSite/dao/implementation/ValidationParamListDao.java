package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ValidationParamListIntDao;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ValidationParamListIntDao")
public class ValidationParamListDao extends AbstractDao implements ValidationParamListIntDao {

    public void save(ValidationParamList object) {
        persist(object);
    }

    public List<ValidationParamList> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationParamList.class);
        return (List<ValidationParamList>)criteria.list();
    }

    public ValidationParamList findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationParamList.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationParamList)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationParamList   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(ValidationParamList object){
        saveOrUpdateIfExist(object);
    }

    public List<ValidationParamList> findByValidationList(ValidationList validationList){
        Criteria criteria = getSession().createCriteria(ValidationParamList.class);
        criteria.add(Restrictions.eq("validationList", validationList));
        return ( List<ValidationParamList>)criteria.list();
    }
}
