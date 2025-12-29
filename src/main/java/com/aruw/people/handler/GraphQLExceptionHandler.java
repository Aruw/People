package com.aruw.people.handler;

import java.util.Map;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.ErrorType;
import com.aruw.people.exception.PersonNotFoundException;
import com.aruw.people.exception.PersonAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;

@ControllerAdvice
public class GraphQLExceptionHandler {

    @GraphQlExceptionHandler(PersonNotFoundException.class)
    public GraphQLError handlePersonNotFound(PersonNotFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .extensions(Map.of("code", "PERSON_NOT_FOUND"))
                .build();
    }

    @GraphQlExceptionHandler(PersonAlreadyExistsException.class)
    public GraphQLError handlePersonAlreadyExists(PersonAlreadyExistsException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.INTERNAL_ERROR)
                .extensions(Map.of("code", "PERSON_ALREADY_EXISTS"))
                .build();
    }

}