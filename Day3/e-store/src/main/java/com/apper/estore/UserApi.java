package com.apper.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        System.out.println(request);
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());

        // Compute age. Review
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        // if age is below 15, throw an InvalidUserAgeException(you must create this exception)
        if (age < 15) {
            throw new InvalidUserAgeException("User must be at least 15 years old");
        }

        return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
    }
}
