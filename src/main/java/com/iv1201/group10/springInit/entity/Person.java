package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents a person entity in the application.
 * This class maps to the person table in the database.
 */
@Entity
@Data
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabetical letters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Surname must contain only alphabetical letters")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Personal number is required")
    @Pattern(regexp = "^\\d{8}-\\d{4}$", message = "Personal number must follow the format YYYYMMDD-XXXX")
    @Column(name = "pnr")
    private String pnr;

    @NotBlank
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only alphabetical letters")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "You need a password.")
    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private Integer roleId;

    @OneToMany(mappedBy = "person")
    private List<CompetenceProfile> competenceProfiles;
}