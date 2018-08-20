package com.someSite.dao.interfaces;

import com.someSite.entity.thirdApplication.SkillValue;


import java.util.List;

public interface SkillValueIntDao {
    public void save(SkillValue object);

    public List<SkillValue> findAll();

    public SkillValue findById(int id);

    public void deleteById(int id);

//    public void update(SkillValue oldObj, SkillValue newObj);

    public void saveOrUpdate(SkillValue object);
}
