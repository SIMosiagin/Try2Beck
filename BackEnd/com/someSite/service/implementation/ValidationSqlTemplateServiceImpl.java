package com.someSite.service.implementation;

import com.someSite.dao.implementation.ValidationDao;
import com.someSite.dao.implementation.ValidationSqlTemplateDao;
import com.someSite.entity.firstApplication.Validation;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationSqlTemplate;
import com.someSite.service.interfaces.ValidationService;
import com.someSite.service.interfaces.ValidationSqlTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ValidationSqlTemplateService")
@Transactional
public class ValidationSqlTemplateServiceImpl implements ValidationSqlTemplateService {

    @Autowired
    private ValidationSqlTemplateDao validationSqlTemplateDao;

    public void save(ValidationSqlTemplate obj) {
        validationSqlTemplateDao.save(obj);
    }

    public List<ValidationSqlTemplate> findAll() {
        return validationSqlTemplateDao.findAll();
    }

    public ValidationSqlTemplate findById(int id) {
        return validationSqlTemplateDao.findById(id);
    }

    public void deleteById(int id) {
        validationSqlTemplateDao.deleteById(id);
    }

    public void saveOrUpdate (ValidationSqlTemplate object){
        validationSqlTemplateDao.saveOrUpdate(object);
    }

    public ValidationSqlTemplate findByValidationList(ValidationList validationList){
        return validationSqlTemplateDao.findByValidationList(validationList);
    }
}
