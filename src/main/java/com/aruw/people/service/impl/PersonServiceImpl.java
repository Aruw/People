package com.aruw.people.service.impl;

import java.util.List;
import java.util.Optional;
import com.aruw.people.model.Person;
import lombok.RequiredArgsConstructor;
import com.aruw.people.dto.PersonInput;
import com.aruw.people.mapper.PersonMapper;
import com.aruw.people.service.PersonService;
import org.springframework.stereotype.Service;
import com.aruw.people.dto.DeletePersonPayload;
import com.aruw.people.repository.PersonRepository;
import com.aruw.people.exception.PersonNotFoundException;
import com.aruw.people.exception.PersonAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return getPersonIfExists(id);
    }

    @Override
    public Person createPerson(PersonInput input) {
        boolean exists = personRepository.existsByDocumentNumber(input.getDocumentNumber());

        if(exists)
            throw new PersonAlreadyExistsException("Person already registered!");

        Person newPerson = personMapper.toModel(input);
        return personRepository.save(newPerson);
    }

    @Override
    public Person updatePerson(Long id, PersonInput input) {
        Person person = getPersonIfExists(id);
        personMapper.updateModel(input, person);
        return personRepository.save(person);
    }

    @Override
    public DeletePersonPayload deleteById(Long id) {
        Person person = getPersonIfExists(id);

        personRepository.deleteById(id);
        return DeletePersonPayload.success(id);
    }

    private Person getPersonIfExists(Long id) {
        Optional<Person> person = personRepository.findById(id);

        if(person.isEmpty())
            throw new PersonNotFoundException(String.format("Person with ID:%d not found!", id));

        return person.get();
    }

}