package com.someSite.entity.secondApplication;

import com.someSite.entity.firstApplication.ColumnList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class SDB {


    final String ALTER_TABLE = "ALTER TABLE ";
    final String ADD_COLUMNS = "ADD COLUMN ";
    final String COMMA = ", ";
    final String INSERT = "INSERT INTO ";

    private String transitTableName;


    @Autowired
    JdbcTemplate jdbcTemplate;

    public void setTransitTable(String transitTableName){
        this.transitTableName = transitTableName;
    }

    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + '"' + transitTableName + '"' + " " +
                "(id SERIAL PRIMARY KEY, " +
                "ISVALID BOOLEAN DEFAULT TRUE, " +
                "row_number INT, " +
                "name VARCHAR(255));");
    }

    public void deleteTable() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS " + '"' + transitTableName + '"');
    }

    public void alterTableColumns(ArrayList<ColumnList> columnListArray){

        if (columnListArray.size() > 0) {

            //        List<String> mapList = jdbcTemplate.queryForList("SELECT information_schema.columns.column_name"+
            //                                                " FROM information_schema.columns" +
            //                                                " WHERE table_schema = 'public'" +
            //                                                " AND table_name   = 'transittable'", String.class);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ALTER_TABLE).append('"').append(transitTableName).append('"').append(" ");

            for (int i = 0; i < columnListArray.size(); i++) {
                stringBuilder.append(ADD_COLUMNS).append(columnListArray.get(i).getName()).append(" ").append(columnListArray.get(i).getType());
                if (columnListArray.get(i).getSize() > 0) {
                    stringBuilder.append("(").append(columnListArray.get(i).getSize()).append(")");
                }
                if (i != columnListArray.size() - 1) {
                    stringBuilder.append(COMMA);
                }
            }
            jdbcTemplate.execute(stringBuilder.toString());
        }
    }

    public void insertToSDB(ArrayList<ColumnList> columnListArray, ArrayList<ArrayList<String>> data){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INSERT).append('"').append(transitTableName).append('"').append(" ").append("(");
        stringBuilder.append("row_number, ").append("name, ");
        for (int i = 0; i < columnListArray.size(); i++) {
            stringBuilder.append(columnListArray.get(i).getName());
            if (i != columnListArray.size() - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(") ").append("VALUES ");

        int countArrayValue = data.size();
        for (ArrayList<String> arrayOfString : data) {
            stringBuilder.append(" (");
            int countArrayOfString = arrayOfString.size();

            for (String str : arrayOfString) {
                stringBuilder.append("'").append(str).append("'");
                countArrayOfString--;
                if (countArrayOfString != 0) {
                    stringBuilder.append(COMMA);
                }
            }
            stringBuilder.append(")");
            countArrayValue--;
            if (countArrayValue != 0) {
                stringBuilder.append(COMMA);
            }

        }

        jdbcTemplate.execute(stringBuilder.toString());
    }

    @Deprecated
    public void insertToIdb(){
        String uploadEployee = new String();
        String uploadSkillValue = new String();

        //setEmployee
        uploadEployee = "insert into employee    (name, sirname) " +
                "select name, sirname " +
                "from sdb_table " +
                "where (name, sirname) not in ( " +
                "  select name, sirname " +
                "  from employee  " +
                ");";

        //setSkillValue
        uploadSkillValue ="merge into skill_employee_rank r\n" +
                "using (" +
                "  select e.employee_id, s.\"MySkillname\" val, 1 skill_id" +
                "  from sdb_table s" +
                "    inner join employee e" +
                "      on s.name = e.name" +
                "      and s.sirname = e.sirname" +
                ") q" +
                "on (r.employee_id = q.employee_id" +
                "  and r.skill_id = q.skill_id)" +
                "when matched then " +
                "  update set r.rank_id = q.value" +
                "  where r.rank_id != q.value" +
                "when not matched then" +
                "  insert (r.employee_id, r.rank_id, r.skill_id)" +
                "  value (q.employee_id, q.rank_id, q.skill_id);";



    }

    public List<Map<String, Object>> selectAllFromTransitTable(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select * from ").append('"').append(transitTableName).append('"').append(" WHERE isvalid = TRUE");
        return jdbcTemplate.queryForList(strBuilder.toString());
    }
























    public List<String> getColumns() {
        return jdbcTemplate.queryForList("SELECT information_schema.columns.column_name" +
                " FROM information_schema.columns" +
                " WHERE table_schema = 'public'" +
                " AND table_name   = '" + transitTableName + "'" +
                "AND information_schema.columns.column_name <> 'id' " +
                "AND information_schema.columns.column_name <> 'isvalid' " +
                "AND information_schema.columns.column_name <> 'row_number' " +
                "AND information_schema.columns.column_name <> 'name'", String.class);
    }



    public void setTransitTableName(String transitTableName) {
        this.transitTableName = transitTableName;
    }



    public List<Map<String, Object>> selectAll() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select * from ").append('"').append(transitTableName).append('"').append(" WHERE isvalid = TRUE");
        return jdbcTemplate.queryForList(strBuilder.toString());
    }

    public List<Map<String, Object>> selectTransitTables() {
        return jdbcTemplate.queryForList("SELECT * FROM transittables");
    }

    public void setInToTransitTables(String tTableName) {
        List<Map<String, Object>> tTables = jdbcTemplate.queryForList("select * from transittables " +
                "where transittables.name_table = '" + tTableName + "'");
        if (tTables.isEmpty()) {
            jdbcTemplate.execute("INSERT INTO transittables (name_table) VALUES ('" + tTableName + "')");
        }
    }

    public void upDateIsValidFalse(List<Map<String, Object>> failValid) {
        if (failValid == null) return;
        for (Map map : failValid) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("UPDATE ").append('"').append(transitTableName).append('"').append(" SET ").
                    append("isValid = false Where id = ").append(map.get("id"));

            jdbcTemplate.execute(strBuilder.toString());
        }
    }

    public List<Map<String, Object>> checkValidInFieldNameByUnique(String field) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select TT.id, TT.row_number, TT.").append('"').append(field).append('"').append(" From ")
                .append('"').append(transitTableName).append('"').append(" TT Where TT.").append('"').append(field).append('"')
                .append(" in (Select ").append('"').append(transitTableName).append('"')
                .append(".").append('"').append(field).append('"').append(" From")
                .append('"').append(transitTableName).append('"')
                .append(" where ").append('"').append(transitTableName).append('"').append(".isvalid = true ")
                .append(" Group By ").append('"').append(transitTableName).append('"').append(".")
                .append('"').append(field).append('"').append(" Having Count(*) > 1)");

        return jdbcTemplate.queryForList(strBuilder.toString());
    }

    public List<Map<String, Object>> checkValidByNotNullField(String field) {

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select TT.id, TT.row_number, TT.").append('"').append(field).append('"').append(" From ")
                .append('"').append(transitTableName).append('"').append(" TT Where TT.").append('"').append(field).append('"')
                .append(" is null").append(" AND TT.isvalid = true");

        return jdbcTemplate.queryForList(strBuilder.toString());
    }

    public List<Map<String, Object>> checkValidByIntField(String field) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("select TT.id , TT.row_number, TT.").append('"').append(field).append('"').append(" From ")
                .append('"').append(transitTableName).append('"').append(" TT Where TT.isvalid = true");

        return jdbcTemplate.queryForList(strBuilder.toString());
    }

    public List<Map<String, Object>> checkManualValidation(String sqlText) {
        try {
            return jdbcTemplate.queryForList(sqlText);
        } catch (Exception ex) {
            return null;
        }
    }

    public HashMap<String, Object> getReport(String head, String typeVal, Map<String, Object> resultVal, String tableName, Integer sizeColumns, Integer count) {

        HashMap<String, Object> report = new HashMap<>();
        report.put("Head", head);
        report.put("Validation sql request", typeVal);
        report.put("Table name", tableName);
        if (sizeColumns == 1) report.put("Lvl", "Field lvl");
        else report.put("Lvl", "Table lvl");

        if (resultVal == null || resultVal.size() == 0) {
            return null;
        } else {
            report.put("Count", count);
            report.putAll(resultVal);

        }
        return report;
    }

    public List<Map<String, Object>> parseVarCharToInt(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> list =new ArrayList<>();
        if (mapList == null) return mapList;
        for (Map m : mapList) {
            Map<String, Object> map = new HashMap<>();
            Set set = m.keySet();
            String id = new String();
            String row_number = new String();
            for (Object srt :set) {
                if (srt.toString().equals("id")) {
                    id = m.get(srt.toString()).toString();
                    continue;
                }
                if (srt.toString().equals("row_number")) {
                    row_number = m.get(srt.toString()).toString();
                    continue;
                }
                try {
                    int tempRank = m.get(srt.toString()) == null ? null : (int) Double.parseDouble(m.get(srt.toString()).toString());
                } catch (NumberFormatException ex) {
                    System.out.println(ex.getStackTrace());
                    map = new HashMap<>();
                    map.put("id", id);
                    map.put("row_number", row_number);
                    map.put(srt.toString(), m.get(srt.toString()));
                    list.add(map);
                }
            }
        }
        return list;
    }
}
