package com.iv1201.group10.springInit.security;

import com.iv1201.group10.springInit.entity.Person;
import com.iv1201.group10.springInit.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PasswordEncryptionCLI implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository; // Assuming you have a service to handle user-related operations

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'yes' to encrypt non-encrypted passwords...");
        String answer = scanner.nextLine(); // Wait for user to press enter
        scanner.close();
        if (answer.matches("yes")) {
            List<Person> fetchedPersons = personRepository.findAll();
            for (Person person : fetchedPersons) {
                if (person.getPassword() != null) {
                    String encryptedPassword = passwordEncoder.encode(person.getPassword());
                    person.setPassword(encryptedPassword);
                    personRepository.save(person);
                }
            }
            System.out.println("Passwords encrypted successfully.");
        }
    }
}