package com.someSite.service.implementation;

import com.someSite.dao.implementation.ColumnListDao;
import com.someSite.dao.implementation.TableListDao;
import com.someSite.entity.firstApplication.ColumnList;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.firstApplication.XlsxList;
import com.someSite.service.interfaces.TableListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service("TableListService")
@Transactional
public class TableListServiceImpl implements TableListService {

    @Autowired
    private TableListDao tableListDao;

    public void save(TableList obj) {
        tableListDao.save(obj);
    }

    public List<TableList> findAll() {
        return tableListDao.findAll();
    }

    public TableList findById(int id) {
        return tableListDao.findById(id);
    }

    public void deleteById(int id) {
        tableListDao.deleteById(id);
    }

    public void saveOrUpdate (TableList object){
        tableListDao.saveOrUpdate(object);
    }

    public List<TableList> findByXlsxList(XlsxList xlsxList){
        return tableListDao.findByXlsxList(xlsxList);
    }

    public TableList findByTableName(String tableName){
        return tableListDao.findByTableName(tableName);
    }
}
