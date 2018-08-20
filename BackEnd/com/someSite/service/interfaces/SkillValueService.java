package com.someSite.service.interfaces;

import com.someSite.entity.thirdApplication.SkillValue;

import java.util.List;
import java.util.Map;

public interface SkillValueService {
    public void save(SkillValue obj);

    public List<SkillValue> findAll();

    //    public ArrayList<Employee> findByFieldString(String field, String value);
//
    public SkillValue findById(int id);

    public void deleteById(int id);

//    public void update(SkillValue newObj,SkillValue oldObj);

    public void saveOrUpdate(SkillValue obj);

    public void uploadToIDB(List<Map<String, Object>> dataFromTransitTable);
}
