package com.someSite.dao.interfaces;

import com.someSite.entity.firstApplication.ColumnList;
import com.someSite.entity.firstApplication.TableList;

import java.util.List;

public interface ColumnListIntDao {

    public void save(ColumnList object);

    public List<ColumnList> findAll();

    public ColumnList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ColumnList object);

    public List<ColumnList> findByTableList(TableList tableList);
}
