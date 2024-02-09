package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private PersonRepository personRepository;

    public Person saveUser(Person person) throws UserAlreadyExistException {

        if (personRepository.findByPnr(person.getPnr()) != null)
            throw new UserAlreadyExistException("Personal number: '" + person.getPnr() + "', is already in use.", "pnr");

        if (personRepository.findByEmail(person.getEmail()) != null)
            throw new UserAlreadyExistException("Email: '" + person.getEmail() + "', is already in use.", "email");

        if (personRepository.findByUsername(person.getUsername()) != null)
            throw new UserAlreadyExistException("Username: '" + person.getUsername() + "', is already in use.", "username");


        person.setRoleId(2);

        personRepository.save(person);
        return person;
    }
}