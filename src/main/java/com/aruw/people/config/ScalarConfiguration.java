package com.aruw.people.config;

import graphql.schema.*;
import graphql.language.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static graphql.scalars.util.Kit.typeName;

@Configuration
public class ScalarConfiguration {

    @Bean
    public GraphQLScalarType bigDecimalScalar() {
        return GraphQLScalarType.newScalar()
            .name("BigDecimal")
            .description("A Java BigDecimal number type.")
            .coercing(new Coercing<BigDecimal, BigDecimal>() {
                @Override
                public BigDecimal serialize(Object dataFetcherResult) {
                    if (dataFetcherResult instanceof BigDecimal bigDecimal) {
                        return bigDecimal;
                    }
                    throw new CoercingSerializeException("Expected a BigDecimal object but received '" + dataFetcherResult.getClass().getName() + "'.");
                }

                @Override
                public BigDecimal parseValue(Object input) {
                    if (input instanceof String stringValue) {
                        try {
                            return new BigDecimal(stringValue);
                        } catch (NumberFormatException e) {
                            throw new CoercingParseValueException("Cannot parse string value to BigDecimal: " + stringValue);
                        }
                    }
                    if (input instanceof BigDecimal bigDecimal) {
                        return bigDecimal;
                    }
                    if (input instanceof Double doubleValue) {
                        return BigDecimal.valueOf((Double) doubleValue);
                    }
                    if (input instanceof Integer intValue) {
                        return BigDecimal.valueOf(((Integer) intValue).longValue());
                    }
                    throw new CoercingParseValueException("Expected a String, BigDecimal, Double, or Integer for BigDecimal input but received '" + typeName(input) + "'.");
                }

                @Override
                public BigDecimal parseLiteral(Object input) {
                    if (input instanceof StringValue stringValue) {
                        try {
                            return new BigDecimal(stringValue.getValue());
                        } catch (NumberFormatException e) {
                            throw new CoercingParseLiteralException("Cannot parse literal string value to BigDecimal: " + stringValue.getValue());
                        }
                    }
                    if (input instanceof FloatValue floatValue) {
                        return floatValue.getValue();
                    }
                    if (input instanceof IntValue intValue) {
                        return new BigDecimal(intValue.getValue());
                    }
                    throw new CoercingParseLiteralException(
                            "Expected a numeric literal for BigDecimal but received '" + typeName(input) + "'."
                    );
                }
            })
            .build();
    }

    @Bean
    public GraphQLScalarType localDateScalar() {
        return GraphQLScalarType.newScalar()
            .name("LocalDate")
            .description("A local date string in YYYY-MM-DD format.")
            .coercing(new Coercing<LocalDate, String>() {

                @Override
                public String serialize(Object dataFetcherResult) {
                    if (dataFetcherResult instanceof LocalDate date) {
                        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    }
                    throw new CoercingSerializeException("Expected a LocalDate object but received '" + dataFetcherResult.getClass().getName() + "'.");
                }

                @Override
                public LocalDate parseValue(Object input) {
                    if (input instanceof String dateString) {
                        try {
                            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException("Invalid LocalDate format for input: '" + input + "'. Expected YYYY-MM-DD.");
                        }
                    }
                    throw new CoercingParseValueException("Expected a String input for LocalDate but received '" + typeName(input) + "'.");
                }

                @Override
                public LocalDate parseLiteral(Object input) {
                    if (input instanceof StringValue stringValue) {
                        return parseValue(stringValue.getValue());
                    }
                    throw new CoercingParseLiteralException("Expected AST StringValue for LocalDate but was '" + typeName(input) + "'.");
                }
            })
            .build();
    }

    @Bean
    public GraphQLScalarType localDateTimeScalar() {
        return GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .description("A local date-time string in ISO-8601 format (YYYY-MM-DDTHH:MM:SS).")
            .coercing(new Coercing<LocalDateTime, String>() {

                @Override
                public String serialize(Object dataFetcherResult) {
                    if (dataFetcherResult instanceof LocalDateTime dateTime) {
                        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                    throw new CoercingSerializeException("Expected a LocalDateTime object but received '" + dataFetcherResult.getClass().getName() + "'.");
                }

                @Override
                public LocalDateTime parseValue(Object input) {
                    if (input instanceof String dateTimeString) {
                        try {
                            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException("Invalid LocalDateTime format for input: '" + input + "'. Expected YYYY-MM-DDTHH:MM:SS.");
                        }
                    }
                    throw new CoercingParseValueException("Expected a String input for LocalDateTime but received '" + typeName(input) + "'.");
                }

                @Override
                public LocalDateTime parseLiteral(Object input) {
                    if (input instanceof StringValue stringValue) {
                        return parseValue(stringValue.getValue());
                    }
                    throw new CoercingParseLiteralException("Expected AST StringValue for LocalDateTime but was '" + typeName(input) + "'.");
                }
            })
            .build();
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType bigDecimalScalar, GraphQLScalarType localDateScalar, GraphQLScalarType localDateTimeScalar) {
        return wiringBuilder -> wiringBuilder
            .scalar(bigDecimalScalar)
            .scalar(localDateScalar)
            .scalar(localDateTimeScalar);
    }

}