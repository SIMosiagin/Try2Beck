package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;

import java.util.List;

public interface ValidationParamListIntDao {
    public void save(ValidationParamList object);

    public List<ValidationParamList> findAll();

    public ValidationParamList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationParamList object);

    public List<ValidationParamList> findByValidationList(ValidationList validationList);
}
