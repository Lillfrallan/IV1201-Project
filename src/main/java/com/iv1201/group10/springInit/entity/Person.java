package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
/**
 * Represents a person entity in the application.
 * This class maps to the person table in the database.
 */
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "person_id")
    private Integer person_id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "pnr")
    private String pnr;

    @Column(name = "role_id")
    private Integer roleId;

    public Person(){}

}
