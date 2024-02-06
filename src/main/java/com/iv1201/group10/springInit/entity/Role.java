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
/**
 * Represents a role entity in the application.
 * This class maps to the role table in the database.
 */
public class Role {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "name")
    private String name;

    public Role(){}


}
