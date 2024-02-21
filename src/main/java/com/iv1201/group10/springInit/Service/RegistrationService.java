package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.exceptions.UserAlreadyExistException;
import com.iv1201.group10.springInit.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(Person person) throws UserAlreadyExistException {
        if (personRepository.findByPnr(person.getPnr()) != null)
            throw new UserAlreadyExistException("Personal number: '" + person.getPnr() + "', is already in use.", "pnr");

        if (personRepository.findByEmail(person.getEmail()) != null)
            throw new UserAlreadyExistException("Email: '" + person.getEmail() + "', is already in use.", "email");

        if (personRepository.findByUsername(person.getUsername()) != null)
            throw new UserAlreadyExistException("Username: '" + person.getUsername() + "', is already in use.", "username");

        String encryptedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encryptedPassword);

        person.setRoleId(2);

        personRepository.save(person);
    }

}