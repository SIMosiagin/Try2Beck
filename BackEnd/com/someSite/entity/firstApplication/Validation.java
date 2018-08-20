package com.someSite.entity.firstApplication;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "validation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "table_list_id", referencedColumnName = "id")
    private TableList tableList;

    @ManyToOne
    @JoinColumn(name = "column_list_id", referencedColumnName = "id")
    private ColumnList columnList;

    @OneToOne
    @JoinColumn(name = "validation_list_id", referencedColumnName = "id")
    private ValidationList validationList;
}
