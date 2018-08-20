package com.someSite.entity.firstApplication;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "validation_sql_template")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationSqlTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "validation_list_id", referencedColumnName = "id")
    private ValidationList validationList;

    @Column(name = "sql_text")
    private String sqlText;

}
