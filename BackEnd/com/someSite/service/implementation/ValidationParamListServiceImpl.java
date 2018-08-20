package com.someSite.service.implementation;

import com.someSite.dao.implementation.ValidationListDao;
import com.someSite.dao.implementation.ValidationParamListDao;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;
import com.someSite.service.interfaces.ValidationListService;
import com.someSite.service.interfaces.ValidationParamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ValidationParamListService")
@Transactional
public class ValidationParamListServiceImpl implements ValidationParamListService {

    @Autowired
    private ValidationParamListDao validationParamListDao;

    public void save(ValidationParamList obj) {
        validationParamListDao.save(obj);
    }

    public List<ValidationParamList> findAll() {
        return validationParamListDao.findAll();
    }

    public ValidationParamList findById(int id) {
        return validationParamListDao.findById(id);
    }

    public void deleteById(int id) {
        validationParamListDao.deleteById(id);
    }

    public void saveOrUpdate (ValidationParamList object){
        validationParamListDao.saveOrUpdate(object);
    }

    public List<ValidationParamList> findByValidationList(ValidationList validationList){
        return validationParamListDao.findByValidationList(validationList);
    }

}
