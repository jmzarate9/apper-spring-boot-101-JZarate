package com.apper.estore.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Email is required") //Both NotNull and NotEmpty ---> to validate that a string is not null and ensures that the value is not empty
    @Email(message = "Invalid Email Format") // used to validate that a string field or method parameter represents a valid email address. It ensures that the value follows the standard email format, including the presence of an "@" symbol
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must have exactly 8 characters")
    private String password;

    @JsonProperty("first_name") //use this annotation to define a different name for a field or method in the JSON representation.
    @NotBlank(message = "First Name is required")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @JsonProperty("birth_date")
    @NotBlank(message = "BirthDay is required")
    // @Pattern used to specify a regular expression pattern that a string field or method parameter must match. It allows you to define a custom pattern that the value must adhere to.
        // the regular expression ^\d{4}-\d{2}-\d{2}$ ensures that the string matches the format "yyyy-dd-mm"
        /*
        * ' ^ ' denotes the start of the string. It ensures that the pattern matches from the beginning of the string.
        * ' \d{4} ' matches exactly four digits. \d represents any digit from 0 to 9, and {4} specifies that it should occur exactly four times.
        * ' $ ' denotes the end of the string. It ensures that the pattern matches until the end of the string.
        *
        * */
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Please use yyyy-dd-mm.")
    private String birthDate;
}
