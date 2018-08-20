package com.someSite.service.interfaces;

import com.someSite.dao.implementation.TableListDao;
import com.someSite.dao.implementation.ValidationDao;
import com.someSite.entity.firstApplication.Validation;

import java.util.List;

public interface ValidationService {

    public void save(Validation obj);

    public List<Validation> findAll();

    public Validation findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(Validation obj);
}
