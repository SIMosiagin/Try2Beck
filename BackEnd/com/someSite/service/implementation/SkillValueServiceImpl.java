package com.someSite.service.implementation;


import com.someSite.dao.interfaces.SkillValueIntDao;
import com.someSite.entity.firstApplication.ColumnList;
import com.someSite.entity.thirdApplication.Employee;
import com.someSite.entity.thirdApplication.EmployeeSkills;
import com.someSite.entity.thirdApplication.SkillDescription;
import com.someSite.service.interfaces.ColumnListService;
import com.someSite.service.interfaces.EmployeeServise;
import com.someSite.service.interfaces.EmployeeSkillsService;
import com.someSite.service.interfaces.SkillValueService;
import com.someSite.entity.thirdApplication.SkillValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("SkillValueService")
@Transactional
public class SkillValueServiceImpl implements SkillValueService {

    @Autowired
    private SkillValueIntDao skillValueIntDao;

    @Autowired
    private EmployeeServise employeeServise;

    @Autowired
    private EmployeeSkillsService employeeSkillsService;

    @Autowired
    private SkillValueService skillValueService;

    @Autowired
    private ColumnListService columnListService;

    public void save(SkillValue obj) {
        skillValueIntDao.save(obj);
    }

    public List<SkillValue> findAll() {
        return skillValueIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public SkillValue findById(int id) {
        return skillValueIntDao.findById(id);
    }

    public void deleteById(int id) {
        skillValueIntDao.deleteById(id);
    }

//    public void update(SkillValue newObj, SkillValue oldObj) {
//        skillValueIntDao.update(newObj, oldObj);
//    }

    public void saveOrUpdate (SkillValue object){
        skillValueIntDao.saveOrUpdate(object);
    }

    public void uploadToIDB(List<Map<String, Object>> dataFromTransitTable){

        List<ColumnList> mappingWithExcel = columnListService.findAll();

        for (Map<String, Object> m: dataFromTransitTable) {

            Employee employee = null;
            Object[] arrayOfMap = m.keySet().toArray();
            for (int i = 3; i <arrayOfMap.length; i++){
                if (i == 3){
                    employee = new Employee();
                    employee.parseString(m.get(arrayOfMap[3]).toString());
                    Employee dBEmployee = employeeServise.findByFirstLastName(employee.getFirstName(),employee.getLastName());
                    if (dBEmployee == null) employeeServise.saveOrUpdate(employee);
                    else employee = dBEmployee;
                }
                else {
                    EmployeeSkills employeeSkills = new EmployeeSkills();
                    employeeSkills.setEmployee(employee);
                    employeeSkills.setSkillDescription(getSkillDescriptionByList(mappingWithExcel, arrayOfMap[i].toString()));
                    employeeSkills.setSkillValue(skillValueService.findById((int) Double.parseDouble(m.get(arrayOfMap[i]).toString())));


                    EmployeeSkills dBEmployeeSkills = employeeSkillsService.findByEmployeeAndSkill(employeeSkills.getEmployee(), employeeSkills.getSkillDescription());
                    if (dBEmployeeSkills == null) employeeSkillsService.save(employeeSkills);
                    else    {
                        dBEmployeeSkills.setSkillValue(employeeSkills.getSkillValue());
                        employeeSkillsService.saveOrUpdate(dBEmployeeSkills);

                    }
                }
            }

        }
    }

    private SkillDescription getSkillDescriptionByList(List<ColumnList> mappingWithExcel, String skillDiscName){

        SkillDescription skillDescription = null;

        for (ColumnList mWE:mappingWithExcel) {
            try {
                if(mWE.getName().replaceAll(String.valueOf('"'),"").equalsIgnoreCase(skillDiscName)){
                    skillDescription = mWE.getSkillDescription();
                    break;
                }
            }
            catch (Exception ex){
                System.out.println(ex);
            }

        }
        return skillDescription;
    }

}
