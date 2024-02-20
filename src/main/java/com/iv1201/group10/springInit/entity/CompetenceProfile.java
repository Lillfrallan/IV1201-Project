package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@Entity
@Table(name = "competence_profile")
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private Integer competenceProfileId;

    // Define personId field to store the ID of the associated person
    @Column(name = "person_id")
    private Integer personId;

    // Define the ManyToOne relationship with the Person entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    // Constructors, getters, and setters
    // Omitted for brevity
}