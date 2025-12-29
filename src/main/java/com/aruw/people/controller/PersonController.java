package com.aruw.people.controller;

import java.util.List;
import jakarta.validation.Valid;
import com.aruw.people.model.Person;
import lombok.RequiredArgsConstructor;
import com.aruw.people.dto.PersonInput;
import com.aruw.people.dto.DeletePersonPayload;
import org.springframework.stereotype.Controller;
import com.aruw.people.service.impl.PersonServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonServiceImpl personService;

    @QueryMapping
    public List<Person> getAllPeople() {
        return personService.findAll();
    }

    @QueryMapping
    public Person getPersonById(@Argument Long id) {
        return personService.findById(id);
    }

    @MutationMapping
    public DeletePersonPayload deletePerson(@Argument Long id) {
        return personService.deleteById(id);
    }

    @MutationMapping
    public Person createPerson(@Argument @Valid PersonInput input) {
        return personService.createPerson(input);
    }

    @MutationMapping
    public Person updatePerson(@Argument Long id, @Argument @Valid PersonInput input) {
        return personService.updatePerson(id, input);
    }

}