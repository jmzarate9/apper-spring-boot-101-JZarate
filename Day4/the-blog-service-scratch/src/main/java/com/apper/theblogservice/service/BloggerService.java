package com.apper.theblogservice.service;

import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//  the service package is commonly used to implement the business logic of an application.
// the service package is to encapsulate and handle the application's business logic independently from the presentation layer (controllers) and data access layer (repositories).
@Service // The @Service annotation acts as a marker to identify a class as a service component.
public class BloggerService {

    // Instance variables
    private final BloggerRepository bloggerRepository;

    // Constructor
    public BloggerService(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    // Method
    public Blogger createBlogger(String email, String name, String password) {
        // Create a new Blogger class and save to blogger variable
        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);

    }

    public Blogger getBlogger(String id) {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);

        return bloggerResult.get();
    }
}
