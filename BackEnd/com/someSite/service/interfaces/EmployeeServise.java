package com.someSite.service.interfaces;


import com.someSite.entity.thirdApplication.Employee;

import java.util.List;

public interface EmployeeServise {

    public void save(Employee obj);

    public List<Employee> findAll();

//    public ArrayList<Employee> findByFieldString(String field, String value);
//
    public Employee findById(int id);

    public void deleteById(int id);

    public void update(Employee newObj, Employee oldObj);

    public void saveOrUpdate(Employee obj);

    public Employee findByFirstLastName(String firstName, String lastName);
}
