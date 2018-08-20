package com.someSite.service.interfaces;

import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationSqlTemplate;

import java.util.List;

public interface ValidationSqlTemplateService {

    public void save(ValidationSqlTemplate obj);

    public List<ValidationSqlTemplate> findAll();

    public ValidationSqlTemplate findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationSqlTemplate obj);

    public ValidationSqlTemplate findByValidationList(ValidationList validationList);
}
