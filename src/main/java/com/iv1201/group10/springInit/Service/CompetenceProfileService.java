package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.repository.CompetenceProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceProfileService {

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    public List<CompetenceProfile> getAllCompetenceProfiles() {
        return competenceProfileRepository.findAll();
    }

    public Optional<CompetenceProfile> getCompetenceProfileById(Integer id) {
        return competenceProfileRepository.findById(id);
    }

    public CompetenceProfile saveCompetenceProfile(CompetenceProfile competenceProfile) {
        return competenceProfileRepository.save(competenceProfile);
    }

    public void deleteCompetenceProfile(Integer id) {
        competenceProfileRepository.deleteById(id);
    }
}
