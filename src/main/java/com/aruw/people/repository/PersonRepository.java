package com.aruw.people.repository;

import com.aruw.people.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByDocumentNumber(String documentNumber);

}