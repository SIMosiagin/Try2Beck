package com.someSite.dao.implementation;


import com.someSite.dao.interfaces.SkillValueIntDao;
import com.someSite.entity.thirdApplication.SkillValue;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SkillValueIntDao")
public class SkillValueDao extends  AbstractDao implements SkillValueIntDao {

    public void save(SkillValue object) {
        persist(object);
    }

    public List<SkillValue> findAll() {
        Criteria criteria = getSession().createCriteria(SkillValue.class);
        return (List<SkillValue>)criteria.list();
    }

    public SkillValue findById(int id) {
        Criteria criteria = getSession().createCriteria(SkillValue.class);
        criteria.add(Restrictions.eq("id", id));
        return (SkillValue)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  skill_value   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

//    public void update(SkillValue oldObj, SkillValue newObj) {
//        String hqlUpdate = "update SkillValue sv " +
//                "set sv.name = :newname, " +
//                "sv.rank = :newrank " +
//                " where sv.name = :oldname" +
//                " and sv.rank = :oldrank";
//        Query query = getSession().createQuery(hqlUpdate);
//        query.setString("newname",newObj.getName());
//        query.setInteger("newrank",newObj.getRank());
//        query.setString("oldname",oldObj.getName());
//        query.setInteger("oldrank",oldObj.getRank());
//        query.executeUpdate();
//    }

    public void saveOrUpdate(SkillValue object){
        saveOrUpdateIfExist(object);
    }

}
