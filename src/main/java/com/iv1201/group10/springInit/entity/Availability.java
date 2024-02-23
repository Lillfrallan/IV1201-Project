package com.iv1201.group10.springInit.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

/**
 * Represents the availability of a person for a certain period when applying.
 */
@Setter
@Getter
@Entity
@Data
@Table(name = "availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personId;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public Availability() {}
}