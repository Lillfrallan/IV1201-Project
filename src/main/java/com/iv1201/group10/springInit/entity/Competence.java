package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "competence_id")
    private Integer competenceId;

    @Column(name = "name")
    private String name;

    public Competence() {
    }
}
