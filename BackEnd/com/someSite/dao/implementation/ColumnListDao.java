package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.ColumnListIntDao;
import com.someSite.entity.firstApplication.ColumnList;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ColumnListIntDao")
public class ColumnListDao extends AbstractDao implements ColumnListIntDao {

    public void save(ColumnList object) {
        persist(object);
    }

    public List<ColumnList> findAll() {
        Criteria criteria = getSession().createCriteria(ColumnList.class);
        return (List<ColumnList>)criteria.list();
    }

    public ColumnList findById(int id) {
        Criteria criteria = getSession().createCriteria(ColumnList.class);
        criteria.add(Restrictions.eq("id", id));
        return (ColumnList)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ColumnList   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(ColumnList object){
        saveOrUpdateIfExist(object);
    }

    public List<ColumnList> findByTableList(TableList tableList) {
        Criteria criteria = getSession().createCriteria(ColumnList.class);
        criteria.add(Restrictions.eq("tableList", tableList));
        return (List<ColumnList>)criteria.list();
    }
}
