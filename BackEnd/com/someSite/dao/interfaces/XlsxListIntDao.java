package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.XlsxList;

import java.util.List;

public interface XlsxListIntDao {
    public void save(XlsxList object);

    public List<XlsxList> findAll();

    public XlsxList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(XlsxList object);
}
