package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Availability;
import com.iv1201.group10.springInit.security.PersonPrincipal;
import com.iv1201.group10.springInit.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;

/**
 * Service class for managing availability-related operations.
 */
@Service
@Transactional
public class ApplyService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    /**
     * Saves the availability for a person the database.
     *
     * @param fromDate The start date of the availability period.
     * @param toDate   The end date of the availability period.
     */
    public void saveAvailability(Date fromDate, Date toDate) {
        PersonPrincipal person = (PersonPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Availability availability = new Availability();
        availability.setId(person.getPerson().getPerson_id());
        availability.setFromDate(fromDate);
        availability.setToDate(toDate);
        availabilityRepository.save(availability);
    }
}