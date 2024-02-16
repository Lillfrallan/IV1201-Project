package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.CompetenceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The CompetenceProfileRepository interface provides methods to perform CRUD operations
 * on CompetenceProfile entities in the database.
 */
@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Integer> {

 /**
  * Retrieves a list of competence profiles by competence ID.
  *
  * @param competence_id the ID of the competence
  * @return a list of competence profiles matching the given competence ID
  */
 List<CompetenceProfile> findByCompetence_competenceId(Integer competence_id);

 /**
  * Retrieves a list of competence profiles by years of experience.
  *
  * @param yearsOfService the years of experience
  * @return a list of competence profiles matching the given years of experience
  */
 List<CompetenceProfile> findByYearsOfExperience(Integer yearsOfService);

 /**
  * Retrieves a list of competence profiles by competence ID and years of experience.
  *
  * @param competence_id      the ID of the competence
  * @param years_of_experience the years of experience
  * @return a list of competence profiles matching the given competence ID and years of experience
  */
 List<CompetenceProfile> findByCompetence_competenceIdAndYearsOfExperience(Integer competence_id, Integer years_of_experience);
}
