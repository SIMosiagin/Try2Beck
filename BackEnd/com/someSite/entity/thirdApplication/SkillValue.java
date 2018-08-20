package com.someSite.entity.thirdApplication;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skill_value")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillValue {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

}
