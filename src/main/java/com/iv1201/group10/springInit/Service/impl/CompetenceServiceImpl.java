package com.iv1201.group10.springInit.Service.impl;

import com.iv1201.group10.springInit.Service.CompetenceService;
import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Override
    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Optional<Competence> getCompetenceById(Integer id) {
        return competenceRepository.findById(id);
    }

    @Override
    public Optional<Competence> getCompetenceByName(String name) {
        return competenceRepository.findByName(name);
    }

    @Override
    public void saveCompetence(Competence competence) {
        competenceRepository.save(competence);
    }

    // Implement other methods as before...
}
