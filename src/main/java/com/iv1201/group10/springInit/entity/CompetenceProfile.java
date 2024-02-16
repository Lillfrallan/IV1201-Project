package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
/**
 * Represents a competence profile entity in the application.
 * This class maps to the competence_profile table in the database.
 */
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "competence_profile_id")
    private Integer competence_profile_id;

    @ManyToOne(fetch = FetchType.LAZY)// Many CompetenceProfiles can belong to one Person
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_id") // name of the foreign key column in the CompetenceProfile table
    private Competence competence;


    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    public CompetenceProfile(){


    }
}


