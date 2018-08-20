package com.someSite.dao.implementation;

import com.someSite.dao.interfaces.EmployeeSkillsIntDao;
import com.someSite.entity.thirdApplication.Employee;
import com.someSite.entity.thirdApplication.EmployeeSkills;
import com.someSite.entity.thirdApplication.SkillDescription;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeSkillsIntDao")
public class EmployeeSkillsDao extends AbstractDao implements EmployeeSkillsIntDao {

    public void save(EmployeeSkills object) {
        persist(object);
    }

    public List<EmployeeSkills> findAll() {
        Criteria criteria = getSession().createCriteria(EmployeeSkills.class);
        return (List<EmployeeSkills>)criteria.list();
    }

    public EmployeeSkills findById(int id) {
        Criteria criteria = getSession().createCriteria(EmployeeSkills.class);
        criteria.add(Restrictions.eq("id", id));
        return (EmployeeSkills)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  employee_skills   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public void update(EmployeeSkills oldObj, EmployeeSkills newObj) {
        String hqlUpdate = "update EmployeeSkills es " +
                "set es.emp_id = :newemp_id, " +
                "es.skill_desc_id = :newskill_desc_id " +
                "es.skill_value_id = :newsskill_value_id " +
                " where es.emp_id = :oldemp_id" +
                " and es.skill_desc_id = :oldskill_desc_id" +
                " and es.skill_value_id = :oldskill_value_id";
        Query query = getSession().createQuery(hqlUpdate);
        query.setEntity("newemp_id",newObj.getEmployee());
        query.setEntity("newskill_desc_id",newObj.getSkillDescription());
        query.setEntity("newsskill_value_id",newObj.getSkillValue());
        query.setEntity("oldemp_id",oldObj.getEmployee());
        query.setEntity("oldskill_desc_id",oldObj.getSkillDescription());
        query.setEntity("oldskill_value_id",oldObj.getSkillValue());
        query.executeUpdate();
    }

    public void saveOrUpdate(EmployeeSkills object){
        saveOrUpdateIfExist(object);
    }

    public EmployeeSkills findByEmployeeAndSkill(Employee employee, SkillDescription skillDescription) {
        Criteria criteria = getSession().createCriteria(EmployeeSkills.class);
        criteria.add(Restrictions.eq("employee", employee));
        criteria.add(Restrictions.eq("skillDescription", skillDescription));
        return (EmployeeSkills) criteria.uniqueResult();
    }
}
