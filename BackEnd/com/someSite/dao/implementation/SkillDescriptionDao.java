package com.someSite.dao.implementation;


import com.someSite.dao.interfaces.SkillDescriptionIntDao;
import com.someSite.entity.thirdApplication.SkillDescription;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SkillDescriptionIntDao")
public class SkillDescriptionDao extends  AbstractDao implements SkillDescriptionIntDao {
    public void save(SkillDescription object) {
        persist(object);
    }

    public List<SkillDescription> findAll() {
        Criteria criteria = getSession().createCriteria(SkillDescription.class);
        return (List<SkillDescription>)criteria.list();
    }

    public SkillDescription findById(int id) {
        Criteria criteria = getSession().createCriteria(SkillDescription.class);
        criteria.add(Restrictions.eq("id", id));
        return (SkillDescription)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  skill_description   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    public void update(SkillDescription oldObj, SkillDescription newObj) {
    }

    public void saveOrUpdate(SkillDescription object){
        saveOrUpdateIfExist(object);
    }

    public SkillDescription findByName(String name){
        Criteria criteria = getSession().createCriteria(SkillDescription.class);
        criteria.add(Restrictions.eq("name", name));
        return (SkillDescription)criteria.uniqueResult();
    }
}
