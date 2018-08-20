package com.someSite.dao.interfaces;


import com.someSite.entity.firstApplication.ValidationList;

import java.util.List;

public interface ValidationListIntDao {

    public void save(ValidationList object);

    public List<ValidationList> findAll();

    public ValidationList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationList object);
}
