package com.someSite.service.implementation;

import com.someSite.dao.implementation.TableListDao;
import com.someSite.dao.implementation.ValidationListDao;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.firstApplication.ValidationList;
import com.someSite.service.interfaces.TableListService;
import com.someSite.service.interfaces.ValidationListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("ValidationListService")
@Transactional
public class ValidationListServiceImpl implements ValidationListService {

    @Autowired
    private ValidationListDao validationListDao;

    public void save(ValidationList obj) {
        validationListDao.save(obj);
    }

    public List<ValidationList> findAll() {
        return validationListDao.findAll();
    }

    public ValidationList findById(int id) {
        return validationListDao.findById(id);
    }

    public void deleteById(int id) {
        validationListDao.deleteById(id);
    }

    public void saveOrUpdate (ValidationList object){
        validationListDao.saveOrUpdate(object);
    }

}
