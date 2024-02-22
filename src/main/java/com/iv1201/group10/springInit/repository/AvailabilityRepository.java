package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Availability entities.
 */
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

}