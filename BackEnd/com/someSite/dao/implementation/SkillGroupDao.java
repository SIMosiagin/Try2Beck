package com.someSite.dao.implementation;


import com.someSite.dao.interfaces.SkillGroupIntDao;
import com.someSite.entity.thirdApplication.SkillGroup;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("SkillGroupIntDao")
public class SkillGroupDao extends  AbstractDao implements SkillGroupIntDao {

    public void save(SkillGroup object) {
        persist(object);
    }

    public List<SkillGroup> findAll() {
        Criteria criteria = getSession().createCriteria(SkillGroup.class);
        return (List<SkillGroup>)criteria.list();
    }

    public SkillGroup findById(int id) {
        Criteria criteria = getSession().createCriteria(SkillGroup.class);
        criteria.add(Restrictions.eq("id", id));
        return (SkillGroup)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  skill_group   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public SkillGroup findByName(String name) {
        Criteria criteria = getSession().createCriteria(SkillGroup.class);
        criteria.add(Restrictions.eq("name", name));
        return (SkillGroup)criteria.uniqueResult();

    }

    public void update(SkillGroup oldObj, SkillGroup newObj) {
        String hqlUpdate = "update SkillGroup  " +
                "set name = :newname, " +
                " description = :newdescription " +
                " where name = :oldname" +
                " and description = :olddescription";
        Query query = getSession().createQuery(hqlUpdate);
        query.setString("newname",newObj.getName());
        query.setString("newdescription",newObj.getDescription());
        query.setString("oldname",oldObj.getName());
        query.setString("olddescription",oldObj.getDescription());
        query.executeUpdate();
    }

    public void saveOrUpdate(SkillGroup object){
        saveOrUpdateIfExist(object);
    }
}
