package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
/**
 * Represents a competence profile entity in the application.
 * This class maps to the competence_profile table in the database.
 */
public class CompetenceProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "competence_profile_id")
    private Integer id;

    @ManyToOne // Many CompetenceProfiles can belong to one Person
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "years_of_experience")
    private int years_of_experience;

    public CompetenceProfile(){


    }
}


