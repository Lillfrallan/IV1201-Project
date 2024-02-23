package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Role entities.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Finds a role name by its role ID.
     *
     * @param roleId The ID of the role name to find.
     * @return The Role object if found, otherwise null.
     */
    public Role findRoleByRoleId(Integer roleId);
}