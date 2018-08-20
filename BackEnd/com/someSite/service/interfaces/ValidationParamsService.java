package com.someSite.service.interfaces;

import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;
import com.someSite.entity.firstApplication.ValidationParams;

import java.util.List;

public interface ValidationParamsService {

    public void save(ValidationParams obj);

    public List<ValidationParams> findAll();

    public ValidationParams findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationParams obj);

    public List<ValidationParams> findByValidationParamList(ValidationParamList validationParamList);
}
