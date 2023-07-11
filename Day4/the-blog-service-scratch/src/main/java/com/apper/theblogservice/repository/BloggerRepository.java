package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// the repository package is typically used to define and implement the data access layer of an application.
// the repository package is to encapsulate database operations and provide an abstraction layer for working with data. It follows the Repository pattern and facilitates the separation of concerns within the application's architecture.
@Repository
public interface BloggerRepository extends CrudRepository<Blogger, String> {
    // CrudRepository<T, ID>
        // ID is the @ID (in Blogger class) which is the primary key
    // CrudRepository is an interface provided by Spring Data that provides basic CRUD (Create, Read, Update, Delete) operations for entities.
    // <Blogger, String> specifies the entity class (Blogger) and the data type of the entity's primary key (String).
}
