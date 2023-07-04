package com.apper.estore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // indicates that it provides centralized exception handling for multiple controllers in the application.
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //annotation specifies that the HTTP response status code should be set to 400 (Bad Request) when this exception is encountered.
    @ExceptionHandler(MethodArgumentNotValidException.class) // @ExceptionHandler(MethodArgumentNotValidException.class) annotation declares that this method should handle MethodArgumentNotValidException specifically.
    @ResponseBody //The @ResponseBody annotation indicates that the return value of the method should be serialized directly into the response body.
    // Within this class, there is a method handleInvalidInputFields that handles the MethodArgumentNotValidException specifically. This exception is typically thrown when the validation of input arguments fails in a controller method annotated with validation annotations such as @Valid.
    public ServiceError handleInvalidInputFields(MethodArgumentNotValidException ex) {
        //In the method implementation, it retrieves the binding result from the exception using ex.getBindingResult(). The binding result contains information about the validation errors that occurred.
            // findFirst() to retrieve the first error, and if it exists, it creates a new ServiceError object using the error's default message. If there are no errors, it creates a generic ServiceError object with the message "Unknown invalid argument encountered".
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    @ExceptionHandler(InvalidUserAgeException.class)
    public ResponseEntity<String> handleInvalidUserAgeException(InvalidUserAgeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
