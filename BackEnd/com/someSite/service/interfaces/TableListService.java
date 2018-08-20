package com.someSite.service.interfaces;

import com.someSite.dao.implementation.ColumnListDao;
import com.someSite.dao.implementation.TableListDao;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.firstApplication.XlsxList;

import java.util.List;

public interface TableListService {

    public void save(TableList obj);

    public List<TableList> findAll();

    public TableList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(TableList obj);

    public  List<TableList> findByXlsxList(XlsxList xlsxList);

    public TableList findByTableName(String tableName);
}
