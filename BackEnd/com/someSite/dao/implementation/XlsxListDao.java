package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.XlsxListIntDao;
import com.someSite.entity.firstApplication.XlsxList;
import com.someSite.entity.thirdApplication.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("XlsxListIntDao")
public class XlsxListDao extends AbstractDao implements XlsxListIntDao {

    public void save(XlsxList object) {
        persist(object);
    }

    public List<XlsxList> findAll() {
        Criteria criteria = getSession().createCriteria(XlsxList.class);
        return (List<XlsxList>)criteria.list();
    }

    public XlsxList findById(int id) {
        Criteria criteria = getSession().createCriteria(XlsxList.class);
        criteria.add(Restrictions.eq("id", id));
        return (XlsxList)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  XlsxList   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void saveOrUpdate(XlsxList object){
        saveOrUpdateIfExist(object);
    }


}
