package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Availability;
import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.repository.AvailabilityRepository;
import com.iv1201.group10.springInit.repository.CompetenceProfileRepository;
import com.iv1201.group10.springInit.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class ApplyService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    public void saveAvailability(Date fromDate, Date toDate) {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        //Person personId = getAuthenticatedUserDetails().getPersonId();
        Person person = getAuthenticatedUser();

        // Create an availability entity
        Availability availability = new Availability();
        availability.setPerson(person);
        availability.setFromDate(fromDate);
        availability.setToDate(toDate);

        // Save the availability to the database
        availabilityRepository.save(availability);
    }
//    public void saveCompetence(Integer yearsOfExperience) {
//        Person person = getAuthenticatedUser();
//
//        CompetenceProfile competenceProfile= new CompetenceProfile();
//        competenceProfile.setYearsOfExperience(yearsOfExperience);
//        competenceProfile.setCompetence();
//        competenceProfile.setPerson(person);
//
//    }






    private Person getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            System.out.println("user details"+ userDetails);
            return personRepository.findByUsername(username);

        } else {
            return null;
        }
    }
    }

//    private CustomUserDetailsPrincipal getAuthenticatedUserDetails() {
//        CustomUserDetailsPrincipal userDetails = (CustomUserDetailsPrincipal) SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//
//        return userDetails;
//    }
//}
//    // Other methods in your service class...



