package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {

}
