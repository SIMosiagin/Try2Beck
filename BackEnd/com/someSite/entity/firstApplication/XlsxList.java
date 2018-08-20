package com.someSite.entity.firstApplication;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "xlsx_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class XlsxList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "xlsx_name")
    private String xlsxName;

    @Column(name = "workSheet_name")
    private String workSheetName;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        XlsxList xlsxList = (XlsxList) object;
        return Objects.equals(xlsxName, xlsxList.xlsxName) &&
                Objects.equals(workSheetName, xlsxList.workSheetName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(xlsxName, workSheetName);
    }
}
