package com.someSite.entity.thirdApplication;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skill_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SkillGroup that = (SkillGroup) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description);
    }
}
