package com.someSite.service.implementation;


import com.someSite.dao.interfaces.SkillDescriptionIntDao;
import com.someSite.service.interfaces.SkillDescriptionService;
import com.someSite.entity.thirdApplication.SkillDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("SkillDescriptionService")
@Transactional
public class SkillDescriptionServiceImpl implements SkillDescriptionService {

    @Autowired
    private SkillDescriptionIntDao skillDescriptionIntDao;

    public void save(SkillDescription obj) {
        skillDescriptionIntDao.save(obj);
    }

    public List<SkillDescription> findAll() {
        return skillDescriptionIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public SkillDescription findById(int id) {
        return skillDescriptionIntDao.findById(id);
    }

    public void deleteById(int id) {
        skillDescriptionIntDao.deleteById(id);
    }

    public void update(SkillDescription newObj, SkillDescription oldObj) {
        skillDescriptionIntDao.update(newObj, oldObj);
    }

    public void saveOrUpdate (SkillDescription object){
        skillDescriptionIntDao.saveOrUpdate(object);
    }

    public SkillDescription findByName(String name){
        return skillDescriptionIntDao.findByName(name);
    }
}
