package com.apper.theblogservice.payload;

import com.apper.theblogservice.model.Blogger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

// TODO: Create a BlogResponse for the Blog
@Data
public class CreateBlogResponse {
    private String id;
    @JsonProperty("blogger_id")
    private String bloggerId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}
