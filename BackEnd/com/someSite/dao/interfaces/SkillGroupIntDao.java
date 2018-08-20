package com.someSite.dao.interfaces;


import com.someSite.entity.thirdApplication.SkillGroup;

import java.util.List;

public interface SkillGroupIntDao {
    public void save(SkillGroup object);

    public List<SkillGroup> findAll();

    public SkillGroup findById(int id);

    public void deleteById(int id);

    public SkillGroup findByName(String name);

    public void update(SkillGroup oldObj, SkillGroup newObj);

    public void saveOrUpdate(SkillGroup object);

}
