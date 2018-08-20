package com.someSite.entity.firstApplication;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "validation_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
