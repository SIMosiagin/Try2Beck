package com.someSite.entity.firstApplication;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "validation_param_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationParamList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToOne
    @JoinColumn(name = "validation_list_id", referencedColumnName = "id")
    private ValidationList validationList;

    @Column(name = "is_multiplate")
    private Boolean isMultiplate;

}
