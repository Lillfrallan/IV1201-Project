package com.iv1201.group10.springInit.entity;
import java.util.Collection;
import java.util.Collections;


import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
@Data
/***
 * Person entity for the person database table
 */
public class Person {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;
    private String surname;
    private String password;
    private String email;
    private String username;
    private String pnr;

    public Person(){

    }

}
