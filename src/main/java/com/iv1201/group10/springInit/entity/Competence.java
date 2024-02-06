package com.iv1201.group10.springInit.entity;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Data
@Entity
public class Competence {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "competence_id")
    private String id;

    @Column(name = "name")
    private String name;

    public Competence() {
    }
}
