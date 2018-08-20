package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;
import com.someSite.entity.firstApplication.ValidationParams;

import java.util.List;

public interface ValidationParamsIntDao {
    public void save(ValidationParams object);

    public List<ValidationParams> findAll();

    public ValidationParams findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationParams object);

    public List<ValidationParams> findByValidationParamList(ValidationParamList validationParamList);
}
