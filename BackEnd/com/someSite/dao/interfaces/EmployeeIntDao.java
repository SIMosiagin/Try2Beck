package com.someSite.dao.interfaces;

import com.someSite.entity.thirdApplication.Employee;

import java.util.List;

public interface EmployeeIntDao {

    public void save(Employee object);

    public List<Employee> findAll();

    public Employee findById(int id);

    public void deleteById(int id);

    public void update(Employee oldObj, Employee newObj);

    public void saveOrUpdate(Employee object);

    public Employee findByFirstLastName(String firstName, String lastName);
}
