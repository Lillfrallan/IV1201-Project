package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Availability;
import com.iv1201.group10.springInit.entity.PersonPrincipal;
import com.iv1201.group10.springInit.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class ApplyService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public void saveAvailability(Date fromDate, Date toDate) {

        PersonPrincipal person = (PersonPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Create an availability entity
        Availability availability = new Availability();
        availability.setPerson(person.getPerson());
        availability.setFromDate(fromDate);
        availability.setToDate(toDate);

        // Save the availability to the database
        availabilityRepository.save(availability);
    }

}


