package com.someSite.entity.firstApplication;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "validation_params")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "validation_id", referencedColumnName = "id")
    private Validation validation;

    @ManyToOne
    @JoinColumn(name = "validation_param_list_id", referencedColumnName = "id")
    private ValidationParamList validationParamList;

}
