package com.iv1201.group10.springInit.Service;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private PersonRepository personRepository;

    public boolean authenticateUser(String username, String password) {
        Person user = personRepository.findByUsername(username);

        return password.matches(user.getPassword());
    }
}
