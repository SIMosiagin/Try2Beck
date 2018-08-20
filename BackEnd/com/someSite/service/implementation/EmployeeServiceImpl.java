package com.someSite.service.implementation;


import com.someSite.dao.interfaces.EmployeeIntDao;
import com.someSite.service.interfaces.EmployeeServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.someSite.entity.thirdApplication.*;

import javax.transaction.Transactional;
import java.util.List;

@Service("EmployeeServise")
@Transactional
public class EmployeeServiceImpl implements EmployeeServise {

    @Autowired
    private EmployeeIntDao employeeIntDao;

    public void save(Employee obj) {
        employeeIntDao.save(obj);
    }

    public List<Employee> findAll() {
        return employeeIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public Employee findById(int id) {
        return employeeIntDao.findById(id);
    }

    public void deleteById(int id) {
        employeeIntDao.deleteById(id);
    }

    public void update(Employee newObj, Employee oldObj) {
        employeeIntDao.update(newObj, oldObj);
    }

    public void saveOrUpdate (Employee object){
        employeeIntDao.saveOrUpdate(object);
    }

    public Employee findByFirstLastName(String firstName, String lastName){
        return employeeIntDao.findByFirstLastName(firstName,lastName);
    }
}
