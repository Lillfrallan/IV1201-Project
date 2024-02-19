package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findRoleByRoleId(Integer roleId);
}
