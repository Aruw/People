package com.aruw.people.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDate;
import java.math.BigDecimal;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.aruw.people.enums.Gender;
import jakarta.validation.constraints.*;
import com.aruw.people.enums.MaritalStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput {

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @Size(max = 100)
    private String nickname;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[A-Za-z0-9\\-\\.\\/]+$", message = "documentNumber contains invalid characters")
    private String documentNumber;

    @NotNull
    @Past(message = "birthDate must be in the past")
    private LocalDate birthDate;

    @NotNull
    private Gender gender;

    @NotNull
    private MaritalStatus maritalStatus;

    @NotBlank
    @Size(max = 100)
    private String nationality;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "bloodType must be one of: A+, A-, B+, B-, AB+, AB-, O+, O-")
    private String bloodType;

    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "heightCm must be positive")
    private BigDecimal heightCm;

    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "weightKg must be positive")
    private BigDecimal weightKg;

    @NotBlank
    @Size(max = 150)
    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "phoneNumber must be a valid phone number (7-15 digits, optional leading +)")
    private String phoneNumber;

}