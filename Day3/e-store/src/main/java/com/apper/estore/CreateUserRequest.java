package com.apper.estore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must have exactly 8 characters")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "First Name is required")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @JsonProperty("birth_date")
    @NotBlank(message = "BirthDay is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Please use yyyy-dd-mm.")
    private String birthDate;
}
