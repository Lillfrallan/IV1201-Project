package com.iv1201.group10.springInit.repository;

import com.iv1201.group10.springInit.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    public Person findByPnr(String pnr);

    public Person findByEmail(String email);

    public Person findByUsername(String username);

}
