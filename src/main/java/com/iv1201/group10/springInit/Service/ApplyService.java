package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Availability;
import com.iv1201.group10.springInit.entity.CompetenceProfile;
import com.iv1201.group10.springInit.repository.AvailabilityRepository;
import com.iv1201.group10.springInit.repository.CompetenceProfileRepository;
import com.iv1201.group10.springInit.security.PersonPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Service class for managing availability-related operations.
 */
@Service
@Transactional
public class ApplyService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    /**
     * Saves the availability for a person the database.
     *
     * @param fromDate The start date of the availability period.
     * @param toDate   The end date of the availability period.
     */
    public void saveAvailability(Date fromDate, Date toDate) {
        PersonPrincipal person = (PersonPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Availability availability = new Availability();
        availability.setPersonId(person.getPerson());
        availability.setFromDate(fromDate);
        availability.setToDate(toDate);
        availabilityRepository.save(availability);
    }

    /**
     * Retrieves the competence profiles associated with the authenticated person.
     *
     * @return a list of competence profiles associated with the authenticated person
     */
    public List<CompetenceProfile> getApplicantStatuses() {
        // Get the authenticated person from the security context
        PersonPrincipal person = (PersonPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Retrieve competence profiles associated with the person's ID
        return competenceProfileRepository.findByPerson_PersonId(person.getPerson().getPersonId());
    }


}