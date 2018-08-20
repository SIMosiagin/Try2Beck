package com.someSite.service.implementation;

import com.someSite.dao.implementation.ValidationParamListDao;
import com.someSite.dao.implementation.ValidationParamsDao;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.entity.firstApplication.ValidationParamList;
import com.someSite.entity.firstApplication.ValidationParams;
import com.someSite.service.interfaces.ValidationParamListService;
import com.someSite.service.interfaces.ValidationParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ValidationParamsService")
@Transactional
public class ValidationParamsServiceImpl implements ValidationParamsService {

    @Autowired
    private ValidationParamsDao validationParamsDao;

    public void save(ValidationParams obj) {
        validationParamsDao.save(obj);
    }

    public List<ValidationParams> findAll() {
        return validationParamsDao.findAll();
    }

    public ValidationParams findById(int id) {
        return validationParamsDao.findById(id);
    }

    public void deleteById(int id) {
        validationParamsDao.deleteById(id);
    }

    public void saveOrUpdate (ValidationParams object){
        validationParamsDao.saveOrUpdate(object);
    }

    public List<ValidationParams> findByValidationParamList(ValidationParamList validationParamList){
        return validationParamsDao.findByValidationParamList(validationParamList);
    }

}
