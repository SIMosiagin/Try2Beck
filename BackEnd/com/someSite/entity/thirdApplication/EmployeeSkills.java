package com.someSite.entity.thirdApplication;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee_skills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    Employee employee;

    @OneToOne
    @JoinColumn(name = "skill_desc_id", referencedColumnName = "id")
    SkillDescription skillDescription;

    @OneToOne
    @JoinColumn(name = "skill_value_id", referencedColumnName = "id")
    SkillValue skillValue;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EmployeeSkills that = (EmployeeSkills) object;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(skillDescription, that.skillDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employee, skillDescription);
    }
}
