package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.ValidationType;

import java.util.List;

public interface ValidationTypeIntDao {
    public void save(ValidationType object);

    public List<ValidationType> findAll();

    public ValidationType findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationType object);
}
