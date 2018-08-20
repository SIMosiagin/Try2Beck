package com.someSite.service.implementation;

import com.someSite.dao.implementation.ValidationDao;
import com.someSite.dao.implementation.ValidationParamsDao;
import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationParams;
import com.someSite.service.interfaces.ValidationParamsService;
import com.someSite.service.interfaces.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ValidationService")
@Transactional
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private ValidationDao validationDao;

    public void save(Validation obj) {
        validationDao.save(obj);
    }

    public List<Validation> findAll() {
        return validationDao.findAll();
    }

    public Validation findById(int id) {
        return validationDao.findById(id);
    }

    public void deleteById(int id) {
        validationDao.deleteById(id);
    }

    public void saveOrUpdate (Validation object){
        validationDao.saveOrUpdate(object);
    }

}
