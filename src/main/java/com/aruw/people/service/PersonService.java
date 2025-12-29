package com.aruw.people.service;

import java.util.List;
import com.aruw.people.model.Person;
import com.aruw.people.dto.PersonInput;
import com.aruw.people.dto.DeletePersonPayload;

public interface PersonService {

    List<Person> findAll();
    Person findById(Long id);
    Person createPerson(PersonInput input);
    DeletePersonPayload deleteById(Long id);
    Person updatePerson(Long id, PersonInput input);

}