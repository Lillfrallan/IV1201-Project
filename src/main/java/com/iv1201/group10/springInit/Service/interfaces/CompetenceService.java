package com.iv1201.group10.springInit.Service.interfaces;

import com.iv1201.group10.springInit.entity.Competence;
import java.util.List;
import java.util.Optional;

public interface CompetenceService {
    List<Competence> getAllCompetences();
    Optional<Competence> getCompetenceById(Integer id);
    Optional<Competence> getCompetenceByName(String name); // New method
    void saveCompetence(Competence competence);

    Double combineExperience(Integer year, Integer month);
}