package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.exceptions.BlogNotFoundException;
import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.payload.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ServiceError handleInvalidInputFields(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    // TODO: Exception handler for the duplicate email address
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseBody
    public ServiceError handleIsRegisteredException(EmailAlreadyRegisteredException exception) {
        return new ServiceError(exception.getMessage());
    }

    // TODO: Exception handler for the Blogger Id not found and the response status should be 404 (NOT_FOUND)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BloggerNotFoundException.class)
    @ResponseBody

    public ServiceError handleIdNotFoundException(BloggerNotFoundException exception) {
        return new ServiceError(exception.getMessage());
    }

    // TODO: Exception handler for the Blog Id not found and the response status should be 404 (NOT_FOUND)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BlogNotFoundException.class)
    @ResponseBody

    public ServiceError handleBlogIdNotFoundException(BlogNotFoundException exception) {
        return new ServiceError(exception.getMessage());
    }


}
