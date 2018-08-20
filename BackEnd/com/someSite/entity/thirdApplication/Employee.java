package com.someSite.entity.thirdApplication;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName);
    }

    public void parseString(String firstAndLastName){


        String[] srt = firstAndLastName.split(" ");

        for (String s:
                srt) {
            if (s.length() ==0){
                continue;
            }
            else if (this.firstName == null){
                this.firstName = s;
            }
            else {
                this.lastName = s;
            }
        }
    }
}
