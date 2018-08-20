package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.Validation;

import java.util.List;

public interface ValidationIntDao {

    public void save(Validation object);

    public List<Validation> findAll();

    public Validation findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(Validation object);
}
