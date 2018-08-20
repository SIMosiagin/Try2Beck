package com.someSite.dao.interfaces;

import com.someSite.entity.thirdApplication.SkillDescription;

import java.util.List;

public interface SkillDescriptionIntDao {
    public void save(SkillDescription object);

    public List<SkillDescription> findAll();

    public SkillDescription findById(int id);

    public void deleteById(int id);

    public void update(SkillDescription oldObj, SkillDescription newObj);

    public void saveOrUpdate(SkillDescription object);

    public SkillDescription findByName(String name);
}
