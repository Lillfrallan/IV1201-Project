package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.repository.CompetenceProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing CompetenceProfile entities.
 */
@Service
public class CompetenceProfileService {

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    /**
     * Saves a competence profile to the database.
     *
     * @param competenceProfile The CompetenceProfile object to save.
     * @return The saved CompetenceProfile object.
     */
    public CompetenceProfile saveCompetenceProfile(CompetenceProfile competenceProfile) {
        return competenceProfileRepository.save(competenceProfile);
    }
}