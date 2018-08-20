package com.someSite.entity.firstApplication;


import com.someSite.entity.thirdApplication.SkillDescription;
import com.someSite.entity.thirdApplication.SkillGroup;
import com.someSite.service.interfaces.ColumnListService;
import com.someSite.service.interfaces.SkillDescriptionService;
import com.someSite.service.interfaces.SkillGroupService;
import com.someSite.service.interfaces.TableListService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class FileExcel {

    private XSSFWorkbook myExcelBook;
    private int amtSheets;
    private String nameFile;
    private String pathReport;
    private XSSFSheet sheet;

    @Autowired
    ColumnListService columnListService;

    @Autowired
    TableListService tableListService;

    @Autowired
    SkillDescriptionService skillDescriptionService;

    @Autowired
    SkillGroupService skillGroupService;

    public FileExcel(MultipartFile file) {
        try {
            this.myExcelBook =  new XSSFWorkbook(file.getInputStream());
            this.amtSheets = myExcelBook.getNumberOfSheets();
            this.nameFile = file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> getSheets(){

        List<Map<String, Object>> listOfMaps = new ArrayList<Map<String, Object>>();


        for (Integer i = 0; i < amtSheets; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name",getSheetNameById(i));
            map.put("id", i);
            listOfMaps.add(map);
        }
        return listOfMaps;

    }


    public String getSheetNameById(Integer id){
        return myExcelBook.getSheetAt(id).getSheetName().toString();
    }

    public ArrayList<ArrayList<String>> getSkillsFromExcel(String sheetName){

        ArrayList<ArrayList<String>> arrayMapOfSkillsExcel = new ArrayList<>();

        sheet = myExcelBook.getSheet(sheetName);
        int countColumn = sheet.getRow(1).getLastCellNum(); // определяем количество столбцов
        String skillGroup = new String(); // переменная в которой мы храним группу
        for (int i = 1; i < countColumn; i++){
            String skill = sheet.getRow(1).getCell(i).getStringCellValue(); // вытаскиваем скилл
            String checkSkillGroup = sheet.getRow(0).getCell(i).getStringCellValue(); // вытаскиваем группу
            skillGroup = checkSkillGroup.isEmpty() ? skillGroup : checkSkillGroup; // проверяем не пустая ли группа пришла из строки выше
            if (!skill.isEmpty()){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(skill);
                arrayList.add(skillGroup);
                arrayMapOfSkillsExcel.add(arrayList);
                continue;
            }
            if (!checkSkillGroup.isEmpty()){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(checkSkillGroup);
                arrayList.add("");
                arrayMapOfSkillsExcel.add(arrayList);

            }

        }

        return arrayMapOfSkillsExcel;
    }

    public ArrayList<ArrayList<String>> getDataFromExcel(ArrayList<ColumnList> columnListArray){
        ArrayList<ArrayList<String>> filterSkills = getSkillsFromExcelWithIndexbyFilterColumnlist(columnListArray);

        ArrayList<ArrayList<String>> listOfData = new ArrayList<>();

        for (int i = 2; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getCell(0).getStringCellValue().isEmpty()){
                break;
            }

            ArrayList<String> listOfValue = new ArrayList<>();
            listOfValue.add(String.valueOf(i-1));
            for (ArrayList<String> str: filterSkills) {
                if (row.getCell(Integer.parseInt(str.get(0))).getCellType() == XSSFCell.CELL_TYPE_NUMERIC ||
                        row.getCell(Integer.parseInt(str.get(0))).getCellType() == XSSFCell.CELL_TYPE_BLANK ){
                    listOfValue.add(String.valueOf(row.getCell(Integer.parseInt(str.get(0))).getNumericCellValue()));
                }
                else if (row.getCell(Integer.parseInt(str.get(0))).getCellType() == XSSFCell.CELL_TYPE_STRING){
                    listOfValue.add(row.getCell(Integer.parseInt(str.get(0))).getStringCellValue());
                }
            }
            listOfData.add(listOfValue);
        }
        return listOfData;

    }

    private ArrayList<ArrayList<String>> getSkillsFromExcelWithIndexbyFilterColumnlist(ArrayList<ColumnList> columnListArray){

        ArrayList<ArrayList<String>> arrayMapOfSkillsExcel = new ArrayList<>();

        int countColumn = sheet.getRow(1).getLastCellNum(); // определяем количество столбцов
        String skillGroup = new String(); // переменная в которой мы храним группу
        for (int i = 0; i < countColumn; i++){
            String skill = sheet.getRow(1).getCell(i).getStringCellValue(); // вытаскиваем скилл
            String checkSkillGroup = sheet.getRow(0).getCell(i).getStringCellValue(); // вытаскиваем группу
            skillGroup = checkSkillGroup.isEmpty() ? skillGroup : checkSkillGroup; // проверяем не пустая ли группа пришла из строки выше
            if (i == 0){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(String.valueOf(i));
                arrayList.add(skill);
                arrayList.add(checkSkillGroup);
                arrayMapOfSkillsExcel.add(arrayList);
                continue;
            }
            if (!skill.isEmpty()){
                if (filterSkillExcelByColumnLists(columnListArray, skill,skillGroup)){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(i));
                    arrayList.add(skill);
                    arrayList.add(checkSkillGroup);
                    arrayMapOfSkillsExcel.add(arrayList);
                    continue;
                }
            }
            if (!checkSkillGroup.isEmpty()){
                if (filterSkillExcelByColumnLists(columnListArray, skill,skillGroup)){
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(i));
                    arrayList.add(checkSkillGroup);
                    arrayList.add("");
                    arrayMapOfSkillsExcel.add(arrayList);
                }
            }
        }
        return arrayMapOfSkillsExcel;
    }

    private boolean filterSkillExcelByColumnLists(ArrayList<ColumnList> columnListArray, String excelSkill, String excelGroup){
        for (ColumnList columnList: columnListArray){
            if (!excelSkill.isEmpty()){
                if (columnList.getXlsxSkill().equalsIgnoreCase(excelSkill) && columnList.getXlsxGroup().equalsIgnoreCase(excelGroup)){
                    return true;
                }
            }
            else if (!excelGroup.isEmpty()){
                if (columnList.getXlsxSkill().equalsIgnoreCase(excelGroup)){
                    return true;
                }
            }
        }
        return false;
    }
}
