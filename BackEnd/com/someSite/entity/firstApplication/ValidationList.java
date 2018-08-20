package com.someSite.entity.firstApplication;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "validation_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ValidationType validationType;

    @Column(name = "is_manual")
    private Boolean isManual;

}
