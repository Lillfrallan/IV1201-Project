package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Competence;
import com.iv1201.group10.springInit.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Competence entities.
 */
@Service
public class CompetenceService implements com.iv1201.group10.springInit.Service.interfaces.CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Competence> getCompetenceById(Integer id) {
        return competenceRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Competence> getCompetenceByName(String name) {
        return competenceRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCompetence(Competence competence) {
        competenceRepository.save(competence);
    }
}