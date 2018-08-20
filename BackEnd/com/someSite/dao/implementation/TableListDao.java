package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.TableListIntDao;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.firstApplication.XlsxList;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TableListIntDao")
public class TableListDao extends AbstractDao implements TableListIntDao {

    public void save(TableList object) {
        persist(object);
    }

    public List<TableList> findAll() {
        Criteria criteria = getSession().createCriteria(TableList.class);
        return (List<TableList>)criteria.list();
    }

    public TableList findById(int id) {
        Criteria criteria = getSession().createCriteria(TableList.class);
        criteria.add(Restrictions.eq("id", id));
        return (TableList)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  TableList   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(TableList object){
        saveOrUpdateIfExist(object);
    }

    public  List<TableList> findByXlsxList(XlsxList xlsxList) {
        Criteria criteria = getSession().createCriteria(TableList.class);
        criteria.add(Restrictions.eq("xlsxList", xlsxList));
        return (List<TableList>)criteria.list();
    }

    public  TableList findByTableName(String tableName) {
        Criteria criteria = getSession().createCriteria(TableList.class);
        criteria.add(Restrictions.eq("name", tableName));
        return (TableList)criteria.uniqueResult();
    }
}
