package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.repository.CompetenceProfileRepository;
import com.iv1201.group10.springInit.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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


    }


