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
public class Role {
    @Id
    @Column(name="role_id")
    private Integer roleId;

    private String name;


}
