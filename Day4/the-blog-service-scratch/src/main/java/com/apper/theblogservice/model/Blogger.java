package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// This model is for the database.
    //  the model package is typically used to define the data model or domain objects of an application.
@Entity // The @Entity annotation is used to mark a Java class as an entity, indicating that it is a persistent object that should be mapped to a corresponding table in the database.
@Table(name = "BLOGGER") // The @Table annotation is used to specify the details of the database table associated with a particular entity class. It is typically applied at the class level of an entity.
@Data
public class Blogger {

    @Id //The @Id annotation is used to mark a field or property of an entity as the primary key. It indicates that the annotated field is uniquely identifying the entity records within the table.
    @Column(name = "ID") //The @Column annotation is used to specify the details of a database column associated with a specific attribute or property of an entity class.
    @GeneratedValue(strategy = GenerationType.UUID) // @GeneratedValue annotation in Spring Boot is used to specify how a primary key or identifier should be generated for an entity. It will generate an id automatically.
    private String id;
    @Column(name = "COMPLETE_NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @PrePersist // The @PrePersist annotation in Spring Boot is used to specify a callback method that should be executed before an entity is persisted to the database.
    // the setCreatedAt() method is annotated with @PrePersist, indicating that it should be called automatically before the entity is persisted for the first time.
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    @PreUpdate // the @PreUpdate method sets the updatedAt field to the current timestamp using LocalDateTime.now(). This ensures that the updatedAt field is updated with the current timestamp every time an update operation is performed on the User entity.
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }

}
