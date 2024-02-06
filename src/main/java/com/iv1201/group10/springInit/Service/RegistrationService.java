package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    public Person saveUser(Person person) throws UserAlreadyExistException {

        if (personRepository.findByPnr(person.getPnr()) != null)
            throw new UserAlreadyExistException("Personal number: '" + person.getPnr() + "', is already in use.", "pnr");

        if (personRepository.findByEmail(person.getEmail()) != null)
            throw new UserAlreadyExistException("Email: '" + person.getEmail() + "', is already in use.", "email");

        if (personRepository.findByUsername(person.getUsername()) != null)
            throw new UserAlreadyExistException("Username: '" + person.getUsername() + "', is already in use.", "username");


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        person.setPassword(encoder.encode(person.getPassword()));

        personRepository.save(person);
        return person;
    }


    /**
     * @param username
     * @return null
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}