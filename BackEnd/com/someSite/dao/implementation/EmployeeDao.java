package com.someSite.dao.implementation;


import com.someSite.dao.interfaces.EmployeeIntDao;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeIntDao")
public class EmployeeDao extends AbstractDao implements EmployeeIntDao {

    public void save(Employee object) {
        persist(object);
    }

    public List<Employee> findAll() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        return (List<Employee>)criteria.list();
    }

    public Employee findById(int id) {
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("id", id));
        return (Employee)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  employee   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }


    public void update(Employee oldObj, Employee newObj) {
        String hqlUpdate = "update Employee e " +
                "set e.first_name = :newfirst_name, " +
                "e.last_name = :newlast_name " +
                " where e.first_name = :oldfirst_name" +
                " and e.last_name = :oldlast_name";
        Query query = getSession().createQuery(hqlUpdate);
        query.setString("newfirst_name",newObj.getFirstName());
        query.setString("newlast_name",newObj.getLastName());
        query.setString("oldfirst_name",oldObj.getFirstName());
        query.setString("oldlast_name",oldObj.getLastName());
        query.executeUpdate();
    }

    public void saveOrUpdate(Employee object){
        saveOrUpdateIfExist(object);
    }

    public Employee findByFirstLastName(String firstName, String lastName){
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("firstName", firstName));
        criteria.add(Restrictions.eq("lastName", lastName));
        return (Employee)criteria.uniqueResult();
    }
}
