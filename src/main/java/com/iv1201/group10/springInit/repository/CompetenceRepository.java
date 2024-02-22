package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing Competence entities.
 */
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
    Optional<Competence> findByName(String name);
}