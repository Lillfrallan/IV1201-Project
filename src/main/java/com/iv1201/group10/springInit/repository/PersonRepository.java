package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Person entities.
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Finds a person by their person number (PNR).
     *
     * @param pnr The person number (PNR) of the person to find.
     * @return The Person object if found, otherwise null.
     */
    public Person findByPnr(String pnr);

    /**
     * Finds a person by their email address.
     *
     * @param email The email address of the person to find.
     * @return The Person object if found, otherwise null.
     */
    public Person findByEmail(String email);

    /**
     * Finds a person by their username.
     *
     * @param username The username of the person to find.
     * @return The Person object if found, otherwise null.
     */
    public Person findByUsername(String username);
}