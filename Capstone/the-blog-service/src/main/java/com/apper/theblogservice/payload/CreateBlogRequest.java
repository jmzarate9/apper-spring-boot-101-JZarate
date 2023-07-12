package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// TODO: Create a BlogRequest for the Blog
@Data
public class CreateBlogRequest {
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "body is required")
    private String body;
    @NotBlank(message = "Blogger Id is required")
    @JsonProperty("blogger_id")
    private String bloggerId;
}
