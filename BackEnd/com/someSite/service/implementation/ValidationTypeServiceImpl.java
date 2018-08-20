package com.someSite.service.implementation;

import com.someSite.dao.implementation.ValidationSqlTemplateDao;
import com.someSite.dao.implementation.ValidationTypeDao;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationSqlTemplate;
import com.someSite.entity.firstApplication.ValidationType;
import com.someSite.service.interfaces.ValidationSqlTemplateService;
import com.someSite.service.interfaces.ValidationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ValidationTypeService")
@Transactional
public class ValidationTypeServiceImpl implements ValidationTypeService {

    @Autowired
    private ValidationTypeDao validationTypeDao;

    public void save(ValidationType obj) {
        validationTypeDao.save(obj);
    }

    public List<ValidationType> findAll() {
        return validationTypeDao.findAll();
    }

    public ValidationType findById(int id) {
        return validationTypeDao.findById(id);
    }

    public void deleteById(int id) {
        validationTypeDao.deleteById(id);
    }

    public void saveOrUpdate (ValidationType object){
        validationTypeDao.saveOrUpdate(object);
    }


}
