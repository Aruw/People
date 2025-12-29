package com.aruw.people.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.aruw.people.model.Person;
import com.aruw.people.dto.PersonInput;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toModel(PersonInput input);

    void updateModel(PersonInput input, @MappingTarget Person person);

}