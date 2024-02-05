package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Availability;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface AvailabilityRepository extends JpaRepository<Availability, String> {

}
