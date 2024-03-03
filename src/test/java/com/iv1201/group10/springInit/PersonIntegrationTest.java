package com.iv1201.group10.springInit;

import com.iv1201.group10.springInit.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Transactional
    public void testSavePerson() {
        // Create a new Person entity
        Person person = new Person();
        person.setName("John");
        person.setSurname("Doe");
        person.setPassword("password");
        person.setEmail("john@example.com");
        person.setUsername("johndoe");
        person.setPnr("1234567890");
        person.setRoleId(1);

        // Save the Person entity to the database
        entityManager.persist(person);

        // Flush the changes to the database
        entityManager.flush();

        // Retrieve the Person entity from the database
        Person savedPerson = entityManager.find(Person.class, person.getPersonId());

        // Assert that the savedPerson matches the original person
        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getName()).isEqualTo("John");
        assertThat(savedPerson.getSurname()).isEqualTo("Doe");
        assertThat(savedPerson.getPassword()).isEqualTo("password");
        assertThat(savedPerson.getEmail()).isEqualTo("john@example.com");
        assertThat(savedPerson.getUsername()).isEqualTo("johndoe");
        assertThat(savedPerson.getPnr()).isEqualTo("1234567890");
        assertThat(savedPerson.getRoleId()).isEqualTo(1);
    }

}