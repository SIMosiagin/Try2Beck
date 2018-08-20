package com.someSite.service.implementation;

import com.someSite.dao.implementation.ValidationTypeDao;
import com.someSite.dao.implementation.XlsxListDao;
import com.someSite.entity.firstApplication.ValidationType;
import com.someSite.entity.firstApplication.XlsxList;
import com.someSite.service.interfaces.ValidationTypeService;
import com.someSite.service.interfaces.XlsxListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("XlsxListService")
@Transactional
public class XlxsListServiceImpl implements XlsxListService {

    @Autowired
    private XlsxListDao xlsxListDao;

    public void save(XlsxList obj) {
        xlsxListDao.save(obj);
    }

    public List<XlsxList> findAll() {
        return xlsxListDao.findAll();
    }

    public XlsxList findById(int id) {
        return xlsxListDao.findById(id);
    }

    public void deleteById(int id) {
        xlsxListDao.deleteById(id);
    }

    public void saveOrUpdate (XlsxList object){
        xlsxListDao.saveOrUpdate(object);
    }

    public XlsxList findOrNew(String xlsxName, String sheetName)
    {
        List<XlsxList> listsOfXlsx = findAll();
        XlsxList xlsxList = new XlsxList();
        xlsxList.setXlsxName(xlsxName);
        xlsxList.setWorkSheetName(sheetName);

        if (listsOfXlsx == null || listsOfXlsx.size() == 0){
            saveOrUpdate(xlsxList);
        }
        else {
            for (XlsxList tmpXlist: listsOfXlsx){
                if (tmpXlist.equals(xlsxList)) return tmpXlist;
            }
        }
        saveOrUpdate(xlsxList);
        return xlsxList;
    }

}
