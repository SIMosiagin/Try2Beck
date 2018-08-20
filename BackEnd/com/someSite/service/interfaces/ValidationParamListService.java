package com.someSite.service.interfaces;

import com.someSite.dao.implementation.ValidationParamListDao;
import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;

import java.util.List;

public interface ValidationParamListService {

    public void save(ValidationParamList obj);

    public List<ValidationParamList> findAll();

    public ValidationParamList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationParamList obj);

    public List<ValidationParamList> findByValidationList(ValidationList validationList);
}
