package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.CompetenceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

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
 List<CompetenceProfile> findByYearsOfExperience(Double yearsOfService);

 /**
  * Retrieves a list of competence profiles by competence ID and years of experience.
  *
  * @param competence_id       the ID of the competence
  * @param years_of_experience the years of experience
  * @return a list of competence profiles matching the given competence ID and years of experience
  */
 List<CompetenceProfile> findByCompetence_competenceIdAndYearsOfExperience(Integer competence_id, Double years_of_experience);

 /**
  * Retrieves an optional containing the competence profile associated with the specified person ID.
  *
  * @param personId The ID of the person associated with the competence profile to retrieve.
  * @return An Optional containing the competence profile with the specified person ID if found, or an empty Optional otherwise.
  */
 Optional<CompetenceProfile> findById(Integer personId);

 /**
  * Retrieves a list of competence profiles by their status.
  *
  * @param status the status used to filter competence profiles
  * @return a list of competence profiles with the specified status
  */
 List<CompetenceProfile> findByStatus(String status);



}