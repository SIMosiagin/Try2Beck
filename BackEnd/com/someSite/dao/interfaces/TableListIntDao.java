package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.firstApplication.XlsxList;

import java.util.List;

public interface TableListIntDao {

    public void save(TableList object);

    public List<TableList> findAll();

    public TableList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(TableList object);

    public List<TableList> findByXlsxList(XlsxList xlsxList);

    public TableList findByTableName(String tableName);

}
