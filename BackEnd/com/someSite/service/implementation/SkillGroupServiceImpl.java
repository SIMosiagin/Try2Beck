package com.someSite.service.implementation;


import com.someSite.dao.interfaces.SkillGroupIntDao;
import com.someSite.service.interfaces.SkillGroupService;
import com.someSite.entity.thirdApplication.SkillGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("SkillGroupService")
@Transactional
public class SkillGroupServiceImpl implements SkillGroupService {

    @Autowired
    private SkillGroupIntDao skillGroupIntDao;

    public void save(SkillGroup obj) {
        skillGroupIntDao.save(obj);
    }

    public List<SkillGroup> findAll() {
        return skillGroupIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public SkillGroup findById(int id) {
        return skillGroupIntDao.findById(id);
    }

    public void deleteById(int id) {
        skillGroupIntDao.deleteById(id);
    }

    public SkillGroup findByName(String name) {
        return skillGroupIntDao.findByName(name);
    }

    public void update(SkillGroup oldObj, SkillGroup newObj) {
        skillGroupIntDao.update(oldObj, newObj);
    }

    public void saveOrUpdate (SkillGroup object){
        skillGroupIntDao.saveOrUpdate(object);
    }
}
