package com.someSite.service.interfaces;

import com.someSite.dao.implementation.ColumnListDao;
import com.someSite.entity.firstApplication.ColumnList;
import com.someSite.entity.firstApplication.TableList;
import com.someSite.entity.thirdApplication.Employee;
import com.someSite.entity.thirdApplication.SkillDescription;

import java.util.List;

public interface ColumnListService {

    public void save(ColumnList obj);

    public List<ColumnList> findAll();

    public ColumnList findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(ColumnList obj);

    public List<ColumnList> findByTableList(TableList tableList);

    public SkillDescription findOrAddSkillByExcel(List<SkillDescription> skillDescriptions, String skillName, String skillGroup);

    public ColumnList findOrAddColumnListByExcel(List<ColumnList> columnList, String excelSkillName, String excelGroupName, TableList tableList, List<SkillDescription> skillDescriptions );

}
