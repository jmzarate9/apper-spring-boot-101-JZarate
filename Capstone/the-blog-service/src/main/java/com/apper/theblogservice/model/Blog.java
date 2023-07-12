package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


// TODO: Create a model for the database Blog --- id, title, body, bloggerId, createdAt, lastUpdate
@Entity
@Table(name = "BLOG")
@Data
public class Blog {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
//    @JoinColumn(name = "BLOGGER_ID")
    private Blogger blogger;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "BODY")
    private String body;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    @PreUpdate
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
