package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ValidationSqlTemplateIntDao;
import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationSqlTemplate;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("ValidationSqlTemplateIntDao")
public class ValidationSqlTemplateDao extends AbstractDao implements ValidationSqlTemplateIntDao {

    public void save(ValidationSqlTemplate object) {
        persist(object);
    }

    public List<ValidationSqlTemplate> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationSqlTemplate.class);
        return (List<ValidationSqlTemplate>)criteria.list();
    }

    public ValidationSqlTemplate findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationSqlTemplate.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationSqlTemplate)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationSqlTemplate   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(ValidationSqlTemplate object){
        saveOrUpdateIfExist(object);
    }

    public ValidationSqlTemplate findByValidationList(ValidationList validationList) {
        Criteria criteria = getSession().createCriteria(ValidationSqlTemplate.class);
        criteria.add(Restrictions.eq("validationList", validationList));
        return (ValidationSqlTemplate)criteria.uniqueResult();
    }
}
