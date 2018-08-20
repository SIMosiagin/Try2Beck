package com.someSite.service.implementation;

import com.someSite.dao.implementation.ColumnListDao;
import com.someSite.dao.interfaces.EmployeeIntDao;
import com.someSite.entity.firstApplication.ColumnList;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.thirdApplication.Employee;
import com.someSite.entity.thirdApplication.SkillDescription;
import com.someSite.entity.thirdApplication.SkillGroup;
import com.someSite.service.interfaces.ColumnListService;
import com.someSite.service.interfaces.SkillDescriptionService;
import com.someSite.service.interfaces.SkillGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service("ColumnListService")
@Transactional
public class ColumnListServiceImpl implements ColumnListService {

    @Autowired
    private ColumnListDao columnListDao;

    @Autowired
    public SkillGroupService skillGroupService;

    @Autowired
    public SkillDescriptionService skillDescriptionService;

    public void save(ColumnList obj) {
        columnListDao.save(obj);
    }

    public List<ColumnList> findAll() {
        return columnListDao.findAll();
    }

    public ColumnList findById(int id) {
        return columnListDao.findById(id);
    }

    public void deleteById(int id) {
        columnListDao.deleteById(id);
    }

    public void saveOrUpdate (ColumnList object){
        columnListDao.saveOrUpdate(object);
    }

    public List<ColumnList> findByTableList(TableList tableList){
        return columnListDao.findByTableList(tableList);
    }

    public SkillDescription findOrAddSkillByExcel(List<SkillDescription> skillDescriptions, String skillName, String skillGroup){
        for (SkillDescription skillDescription: skillDescriptions){
            if (skillGroup.isEmpty()){
                if (skillDescription.getName().equalsIgnoreCase(skillName)){
                    return skillDescription;
                }
                else if(skillDescription.getName().equalsIgnoreCase(skillName)
                        && skillDescription.getSkillGroup().getName().equalsIgnoreCase(skillGroup)) {
                    return skillDescription;
                }
            }
        }

        SkillGroup skillGroupObj = null;
        if (!skillGroup.isEmpty()){
            skillGroupObj = skillGroupService.findByName(skillGroup);
            if (skillGroupObj == null){
                skillGroupObj = new SkillGroup();
                skillGroupObj.setName(skillGroup);
                skillGroupObj.setDescription(new String());
                skillGroupService.save(skillGroupObj);
            }
        }

        SkillDescription skillDescription = new SkillDescription();
        skillDescription.setName(skillName);
        skillDescription.setSkillGroup(skillGroupObj);
        skillDescription.setDescription(new String());
        skillDescriptionService.save(skillDescription);

        return skillDescription;
    }

    public ColumnList findOrAddColumnListByExcel(List<ColumnList> columnLists, String excelSkillName, String excelGroupName, TableList tableList, List<SkillDescription> skillDescriptions ){
        for (ColumnList columnList: columnLists){
            if (excelGroupName.isEmpty()){
                if (columnList.getXlsxSkill().equalsIgnoreCase(excelSkillName)){
                    return columnList;
                }
            }
            else if (columnList.getXlsxSkill().equalsIgnoreCase(excelSkillName)
                    && columnList.getXlsxGroup().equalsIgnoreCase(excelGroupName)) {
                return columnList;
            }

        }

        ColumnList columnList1 = new ColumnList();
        columnList1.setXlsxSkill(excelSkillName);
        columnList1.setXlsxGroup(excelGroupName);
        columnList1.setName('"' + excelSkillName + '"');
        columnList1.setType("VARCHAR");
        columnList1.setSize(255);
        columnList1.setTableList(tableList);
        columnList1.setSkillDescription(findOrAddSkillByExcel(skillDescriptions, excelSkillName, excelGroupName));
        save(columnList1);

        return columnList1;
    }
}
