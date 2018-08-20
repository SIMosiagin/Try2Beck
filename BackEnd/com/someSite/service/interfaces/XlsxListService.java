package com.someSite.service.interfaces;

import com.someSite.entity.firstApplication.ValidationType;
import com.someSite.entity.firstApplication.XlsxList;

import java.util.List;

public interface XlsxListService {

    public void save(XlsxList obj);

    public List<XlsxList> findAll();

    public XlsxList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(XlsxList obj);

    public XlsxList findOrNew(String xlsxName, String sheetName);
}
