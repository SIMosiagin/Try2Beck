package com.someSite.entity.firstApplication;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "table_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TableList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "xlsx_list_id", referencedColumnName = "id")
    private XlsxList xlsxList;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TableList tableList = (TableList) object;
        return Objects.equals(name, tableList.name) &&
                Objects.equals(createdDate, tableList.createdDate) &&
                Objects.equals(modifiedDate, tableList.modifiedDate) &&
                Objects.equals(xlsxList, tableList.xlsxList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, createdDate, modifiedDate, xlsxList);
    }
}
