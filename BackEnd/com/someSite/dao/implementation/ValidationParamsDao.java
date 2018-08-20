package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ValidationParamsIntDao;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;
import com.someSite.entity.firstApplication.ValidationParams;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ValidationParamsIntDao")
public class ValidationParamsDao extends AbstractDao implements ValidationParamsIntDao {

    public void save(ValidationParams object) {
        persist(object);
    }

    public List<ValidationParams> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationParams.class);
        return (List<ValidationParams>)criteria.list();
    }

    public ValidationParams findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationParams.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationParams)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationParams   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(ValidationParams object){
        saveOrUpdateIfExist(object);
    }

    public List<ValidationParams> findByValidationParamList(ValidationParamList validationParamList){
        Criteria criteria = getSession().createCriteria(ValidationParams.class);
        criteria.add(Restrictions.eq("validationParamList", validationParamList));
        return (List<ValidationParams>)criteria.list();
    }
}
