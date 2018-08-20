package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationSqlTemplate;

import java.util.List;

public interface ValidationSqlTemplateIntDao {
    public void save(ValidationSqlTemplate object);

    public List<ValidationSqlTemplate> findAll();

    public ValidationSqlTemplate findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationSqlTemplate object);

    public ValidationSqlTemplate findByValidationList(ValidationList validationList);
}
