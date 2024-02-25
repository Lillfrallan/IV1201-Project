package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.repository.CompetenceProfileRepository;
import com.iv1201.group10.springInit.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing recruitment-related operations.
 */
@Service
public class RecruitmentService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    /**
     * Retrieves all competences from the competence repository.
     * @return A list of all competences.
     */
    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    /**
     * Retrieves all competence profiles from the competence profile repository.
     * @return A list of all competence profiles.
     */
    public List<CompetenceProfile> getAllCompetenceId(){return competenceProfileRepository.findAll();}


    /**
     * Retrieves competence profiles by the given competence ID.
     * @param competenceId The ID of the competence to filter by.
     * @return A list of competence profiles filtered by the given competence ID.
     */
    public List<CompetenceProfile> getProfilesByCompetenceProfileId(Integer competenceId) {
        return competenceProfileRepository.findByCompetence_competenceId(competenceId);
    }

    /**
     * Retrieves competence profiles by the given years of experience.
     * @param yearsOfService The years of experience to filter by.
     * @return A list of competence profiles filtered by the given years of experience.
     */
    public List<CompetenceProfile> getProfilesByYearsOfExperience(Integer yearsOfService){
        return competenceProfileRepository.findByYearsOfExperience(yearsOfService);
    }

    /**
     * Retrieves competence profiles by the given competence ID and years of experience.
     * @param competenceId The ID of the competence to filter by.
     * @param years The years of experience to filter by.
     * @return A list of competence profiles filtered by the given competence ID and years of experience.
     */
    public List<CompetenceProfile> getProfilesByCompetenceIdAndYears(Integer competenceId, Integer years) {
        return competenceProfileRepository.findByCompetence_competenceIdAndYearsOfExperience(competenceId, years);}

    /**
     * Updates the status of a competence profile with the given profile ID.
     *
     * @param profileId The ID of the competence profile to update.
     * @param status The new status to set for the competence profile.
     */
    public void updateStatus(Integer profileId, String status) {
        Optional<CompetenceProfile> optionalProfile = competenceProfileRepository.findById(profileId);
        CompetenceProfile profile = optionalProfile.get();
        profile.setStatus(status);
        competenceProfileRepository.save(profile);
    }

    /**
     * Retrieves a competence profile by its ID.
     *
     * @param profileId The ID of the competence profile to retrieve.
     * @return The competence profile with the specified ID, or null if not found.
     */
    public CompetenceProfile getCompetenceProfileById(Integer profileId) {
        Optional<CompetenceProfile> competenceProfileOptional = competenceProfileRepository.findById(profileId);
        return competenceProfileOptional.orElse(null);
    }

    public List<CompetenceProfile> getProfilesByStatus(String status) {
        return competenceProfileRepository.findByStatus(status);
    }


}


