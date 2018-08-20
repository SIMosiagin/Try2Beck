package com.someSite.service.interfaces;

import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationType;

import java.util.List;

public interface ValidationTypeService {

    public void save(ValidationType obj);

    public List<ValidationType> findAll();

    public ValidationType findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ValidationType obj);
}
