package com.someSite.service.interfaces;

import com.someSite.entity.thirdApplication.SkillGroup;

import java.util.List;

public interface SkillGroupService {
    public void save(SkillGroup obj);

    public List<SkillGroup> findAll();

    //    public ArrayList<Employee> findByFieldString(String field, String value);
//
    public SkillGroup findById(int id);

    public void deleteById(int id);

    public SkillGroup findByName(String name);

    public void update(SkillGroup oldObj, SkillGroup newObj);

    public void saveOrUpdate(SkillGroup object);

}
