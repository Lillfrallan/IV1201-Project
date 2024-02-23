package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a competence profile associated with a persons competences.
 */
@Getter
@Setter
@Data
@Entity
@Table(name = "competence_profile")
public class CompetenceProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private Integer competenceProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "status")
    private String status;


    public CompetenceProfile(){


    }
}