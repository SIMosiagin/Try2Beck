package com.someSite.controller;


import com.someSite.entity.firstApplication.*;
import com.someSite.entity.secondApplication.SDB;
import com.someSite.entity.thirdApplication.SkillDescription;
import com.someSite.entity.thirdApplication.SkillGroup;
import com.someSite.service.interfaces.*;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    FileExcel fileExcel;

    @Autowired
    ColumnListService columnListService;

    @Autowired
    EmployeeServise employeeServise;

    @Autowired
    EmployeeSkillsService employeeSkillsService;

    @Autowired
    SkillDescriptionService skillDescriptionService;

    @Autowired
    SkillGroupService skillGroupService;

    @Autowired
    SkillValueService skillValueService;

    @Autowired
    TableListService tableListService;

    @Autowired
    ValidationListService validationListService;

    @Autowired
    ValidationParamListService validationParamListService;

    @Autowired
    ValidationParamsService validationParamsService;

    @Autowired
    ValidationService validationService;

    @Autowired
    ValidationSqlTemplateService validationSqlTemplateService;

    @Autowired
    ValidationTypeService validationTypeService;

    @Autowired
    XlsxListService xlsxListService;

    @Autowired
    SDB sdb;

    @RequestMapping(value = "/testOne", method = RequestMethod.GET)
    public List<ValidationType> test(){
        ValidationType validation = new ValidationType();
        validation.setName("table lvl");
        validationTypeService.saveOrUpdate(validation);

        ValidationType validation1 = new ValidationType();
        validation1.setName("field lvl");
        validationTypeService.saveOrUpdate(validation1);

        return validationTypeService.findAll();
    }

    @RequestMapping(value = "/sometest", method = RequestMethod.POST)
    public void testTwo(@RequestBody TableList test){
        System.out.println(test);
    }


    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    List<Map<String, Object>> uploadExcel(@RequestParam(value = "excel", required = true) MultipartFile file) throws IOException {
        fileExcel = new FileExcel(file);
        List<Map<java.lang.String, Object>> listOfMaps = fileExcel.getSheets();
        return listOfMaps;
    }

    @RequestMapping(value = "/chousedSheet/{name}",method = RequestMethod.GET)
    public List<TableList> setSheet(@PathVariable("name") String sheetName){

        XlsxList xlsxList = xlsxListService.findOrNew(fileExcel.getNameFile(),sheetName);

        List<TableList> tableLists = tableListService.findByXlsxList(xlsxList);
        if (tableLists == null || tableLists.isEmpty()){
            TableList tableList = new TableList();
            tableList.setXlsxList(xlsxList);
            tableLists.add(tableList);
        }
        return tableLists;
    }

    @RequestMapping(value = "/uploadXlsxList", method = RequestMethod.PUT)
    public void saveOrUpdateXlsxList(@RequestBody ArrayList<TableList> tableLists){
        for (TableList tableList: tableLists){
            if (tableList.getId() == null){
                tableListService.save(tableList);
            }
        }
    }

    @RequestMapping(value = "/getMapping/", method = RequestMethod.GET)
    public ArrayList<ColumnList> getMapping(@RequestParam String table, @RequestParam String sheet){

        ArrayList<ColumnList> arrayListForReturn = new ArrayList<>(); //возвращаемый массив
        ArrayList<ArrayList<String>> arrayMapOfSkillsExcel = fileExcel.getSkillsFromExcel(sheet); //беру скилы из екселя
        TableList tableList = tableListService.findByTableName(table); //нахожу транзитную таблицу
        List<ColumnList> columnLists = columnListService.findByTableList(tableList); // вытаскиваю маппинг по транзитной
        List<SkillDescription> skillDescriptions = skillDescriptionService.findAll(); // вытаскиваю скилы, охриненный оптимизатор (нет)


        if (columnLists == null || columnLists.isEmpty()){
            for (ArrayList<String> skillExcel: arrayMapOfSkillsExcel){
                ColumnList columnList = new ColumnList();
                columnList.setXlsxSkill(skillExcel.get(0));
                columnList.setXlsxGroup(skillExcel.get(1));
                columnList.setName('"' + skillExcel.get(0) + '"');
                columnList.setType("VARCHAR");
                columnList.setSize(255);
                columnList.setTableList(tableList);
                columnList.setSkillDescription(columnListService.findOrAddSkillByExcel(skillDescriptions, skillExcel.get(0), skillExcel.get(1)));
                columnListService.save(columnList);
                arrayListForReturn.add(columnList);
            }
        }
        else {
            for (ArrayList<String> skillExcel: arrayMapOfSkillsExcel){
                    arrayListForReturn.add(columnListService.findOrAddColumnListByExcel(columnLists,skillExcel.get(0),
                            skillExcel.get(1), tableList,skillDescriptions));
            }
        }
        return arrayListForReturn;

    }

    @RequestMapping(value = "getTableList", method = RequestMethod.GET)
    public TableList getTableList(@RequestParam String table){
        return tableListService.findByTableName(table);
    }

    @RequestMapping(value = "/getSkillGroup",method = RequestMethod.GET)
    public List<SkillGroup> getSkillGroup(){
            return skillGroupService.findAll();
    }

    @RequestMapping(value = "/updateMappingSkills", method = RequestMethod.POST)
    public void updateMappingSkills(@RequestBody ArrayList<ColumnList> columnListArray){
        List<SkillDescription> skillDescriptionList = skillDescriptionService.findAll();
        for (ColumnList columnList: columnListArray){
            if (skillDescriptionList.contains(columnList.getSkillDescription())) continue;
            else skillDescriptionService.saveOrUpdate(columnList.getSkillDescription());
        }
    }

    @RequestMapping(value = "/updateMappingFields", method = RequestMethod.POST) //in this POST, we also upload in SDB
    public void updateMappingFields(@RequestBody ArrayList<ColumnList> columnListArray){
        List<ColumnList> listOfColumns = columnListService.findByTableList(columnListArray.get(0).getTableList());
        for (ColumnList columnList: columnListArray){
            if (listOfColumns.contains(columnList)) continue;
            else columnListService.saveOrUpdate(columnList);
        }

        sdb.setTransitTable(columnListArray.get(0).getTableList().getName());
        sdb.deleteTable();
        sdb.createTable();
        sdb.alterTableColumns(columnListArray);
        sdb.insertToSDB(columnListArray, fileExcel.getDataFromExcel(columnListArray));
    }

    @RequestMapping(value = "/uploadFromSDBToIDB", method = RequestMethod.GET)
    public void uploadFromSDBToIDB(){
         //sdb.insertToIdb();
        List<Map<String, Object>> dataFromTransitTable =  sdb.selectAllFromTransitTable();
        skillValueService.uploadToIDB(dataFromTransitTable);
    }

    @RequestMapping(value = "/getValidations", method = RequestMethod.GET)
    public List<Validation> getValidations(){
        return validationService.findAll();
    }

    @RequestMapping(value = "/getValidationList", method = RequestMethod.GET)
    public List<ValidationList> getValidationList(){
        return validationListService.findAll();
    }

    @RequestMapping(value = "/getValidationParamList", method = RequestMethod.GET)
    public List<ValidationParamList> getValidationParamList(){
        return validationParamListService.findAll();
    }

    @RequestMapping(value = "/getValidationParams", method = RequestMethod.GET)
    public List<ValidationParams> getValidationParams(){
        return validationParamsService.findAll();
    }

    @RequestMapping(value = "/getValidationSqlTemplate", method = RequestMethod.GET)
    public List<ValidationSqlTemplate> getValidationSqlTemplate(){
        return validationSqlTemplateService.findAll();
    }

    @RequestMapping(value = "/getValidationType", method = RequestMethod.GET)
    public List<ValidationType> getValidationType(){
        return validationTypeService.findAll();
    }

    @RequestMapping(value = "/setValidationList", method = RequestMethod.POST)
    public List<ValidationList> setValidationList(@RequestBody ArrayList<ValidationList> validationLists){
        for (ValidationList validationList :validationLists){
            validationListService.saveOrUpdate(validationList);
        }
        return validationListService.findAll();
    }

    @RequestMapping(value = "/putValidation", method = RequestMethod.PUT)
    public Validation putValidation(@RequestBody Validation validation){
        validationListService.saveOrUpdate(validation.getValidationList());
        validationService.saveOrUpdate(validation);
        return validation;
    }

    @RequestMapping(value = "/putValidationParamsList", method = RequestMethod.PUT)
    public ValidationParamList putValidationParamsList(@RequestBody ValidationParamList validationParamList){
        validationParamListService.saveOrUpdate(validationParamList);
        return validationParamList;
    }

    @RequestMapping(value = "/putValidationParamsListArray", method = RequestMethod.PUT)
    public ArrayList<ValidationParamList> putValidationParamsListArray(@RequestBody ArrayList<ValidationParamList>  validationParamList){
        for (ValidationParamList validationParamList1: validationParamList){
            validationParamListService.saveOrUpdate(validationParamList1);
        }
        return validationParamList;
    }

    @RequestMapping(value = "/putValidationListArray", method = RequestMethod.PUT)
    public ArrayList<ValidationList> putValidationListArray(@RequestBody ArrayList<ValidationList>  validationLists){
        for (ValidationList validationList: validationLists){
            validationListService.saveOrUpdate(validationList);
        }
        return validationLists;
    }

    @RequestMapping(value = "/putValidationSQL", method = RequestMethod.PUT)
    public List<ValidationSqlTemplate> putValidationSQLArray(@RequestBody ValidationSqlTemplate  validationSqlTemplates){
        validationSqlTemplateService.saveOrUpdate(validationSqlTemplates);
        return validationSqlTemplateService.findAll();
    }

    @RequestMapping(value = "/putValidationParamsArray", method = RequestMethod.PUT)
    public ArrayList<ValidationParams> putValidationParamsArray(@RequestBody ArrayList<ValidationParams>  validationParams){
        for (ValidationParams validationParams1: validationParams){
            validationParamsService.saveOrUpdate(validationParams1);
        }
        return validationParams;
    }

    @RequestMapping(value = "/removeValidation/{id}", method = RequestMethod.DELETE)
    public void removeValidation(@PathVariable("id") Integer id ){
        validationService.deleteById(id);
    }

    @RequestMapping(value = "/removeValidationParams/{id}", method = RequestMethod.DELETE)
    public void removeValidationParams(@PathVariable("id") Integer id ){
        validationParamsService.deleteById(id);
    }

    @RequestMapping(value = "/removeValidationParamsList/{id}", method = RequestMethod.DELETE)
    public void removeValidationParamsList(@PathVariable("id") Integer id ){
        validationParamListService.deleteById(id);
    }

    @RequestMapping(value = "/executeValidation", method = RequestMethod.POST)
    public String executeValidation(@RequestBody ArrayList<Validation> validationArrayList){
        for (Validation validation: validationArrayList){

        }

        return "Done.";
    }









}
