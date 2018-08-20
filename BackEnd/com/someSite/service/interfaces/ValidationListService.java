package com.someSite.service.interfaces;

import com.someSite.dao.implementation.ValidationDao;
import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationList;

import java.util.List;

public interface ValidationListService {

    public void save(ValidationList obj);

    public List<ValidationList> findAll();

    public ValidationList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationList obj);
}
