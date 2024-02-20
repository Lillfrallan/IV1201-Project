package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Availability;
import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.repository.AvailabilityRepository;
import com.iv1201.group10.springInit.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class ApplyService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private PersonRepository personRepository;

    public void saveAvailability(Integer personId, Date fromDate, Date toDate) {
        // Retrieve the person from the database
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        // Create an availability entity
        Availability availability = new Availability();
        availability.setPerson(person);
        availability.setFromDate(fromDate);
        availability.setToDate(toDate);

        // Save the availability to the database
        availabilityRepository.save(availability);
    }

    // Other methods in your service class...
}

