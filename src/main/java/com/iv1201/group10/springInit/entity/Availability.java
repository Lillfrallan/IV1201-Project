package com.iv1201.group10.springInit.entity;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
@Data
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "from_date")
    private String fromDate;

    @Column(name = "to_date")
    private String toDate;

    public Availability() {
    }
}
