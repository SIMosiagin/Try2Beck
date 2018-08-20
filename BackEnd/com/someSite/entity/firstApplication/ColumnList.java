package com.someSite.entity.firstApplication;

import lombok.*;
import com.someSite.entity.thirdApplication.SkillDescription;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "column_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ColumnList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Integer size;

    @Column(name = "xlsx_skill")
    private String xlsxSkill;

    @Column(name = "xlsx_group")
    private String xlsxGroup;

    @ManyToOne
    @JoinColumn(name = "table_list_id", referencedColumnName = "id")
    private TableList tableList;

    @OneToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private SkillDescription skillDescription;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ColumnList that = (ColumnList) object;
        return Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(size, that.size) &&
                Objects.equals(xlsxSkill, that.xlsxSkill) &&
                Objects.equals(xlsxGroup, that.xlsxGroup) &&
                Objects.equals(tableList, that.tableList) &&
                Objects.equals(skillDescription, that.skillDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, size, xlsxSkill, xlsxGroup, tableList, skillDescription);
    }
}
