package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.BlogNotFoundException;
import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.TheBlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TheBlogServiceApi {

    private final TheBlogService theBlogService;

    public TheBlogServiceApi(TheBlogService theBlogService) {
        this.theBlogService = theBlogService;
    }

    // TODO: Create a registerBlogger --> 201 CREATED
    @PostMapping("/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse registerBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailAlreadyRegisteredException {

        System.out.println(request);

        Blogger registerBlogger = theBlogService.registerBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(registerBlogger.getId());
        response.setDateRegistration(registerBlogger.getCreatedAt());

        return response;
    }


    // TODO: Get Blogger --> 200 OK
    @GetMapping("/blogger/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BloggerDetailsResponse getBlogger(@PathVariable String id) throws BloggerNotFoundException {
        Blogger blogger = theBlogService.getBlogger(id);

        BloggerDetailsResponse bloggerDetailsResponse = new BloggerDetailsResponse();
        bloggerDetailsResponse.setId(blogger.getId());
        bloggerDetailsResponse.setName(blogger.getName());
        bloggerDetailsResponse.setEmail(blogger.getEmail());
        bloggerDetailsResponse.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetailsResponse;
    }

    // TODO: Get All Bloggers --> 200 OK
    @GetMapping("/blogger")
    @ResponseStatus(HttpStatus.OK)
    public List<BloggerDetailsResponse> getAllBloggers() {
        List<BloggerDetailsResponse> bloggerDetailsResponseList = theBlogService.getAllBloggers().stream()
                //the stream() method to iterate over the list of blogger.
                .map (blogger -> {
                    //The map() method then creates a new stream of BloggerDetailsResponse objects from the stream of Blogger objects.
                    BloggerDetailsResponse bloggerDetailsResponse = new BloggerDetailsResponse();
                    bloggerDetailsResponse.setId(blogger.getId());
                    bloggerDetailsResponse.setName(blogger.getName());
                    bloggerDetailsResponse.setEmail(blogger.getEmail());
                    bloggerDetailsResponse.setDateRegistration(blogger.getCreatedAt());
                    return bloggerDetailsResponse;
                })
                .collect(Collectors.toList());
                // the collect() method uses the Collectors.toList() method to collect the results of the map() operation into a list.
        return bloggerDetailsResponseList;
    }


    // TODO: Create a createBlog --> 201 CREATED
    @PostMapping("/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) throws BloggerNotFoundException {

        Blog createBlog = theBlogService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createBlog.getId());
        response.setBloggerId(createBlog.getBlogger().getId());
        response.setCreatedAt(createBlog.getCreatedAt());
        response.setLastUpdated(createBlog.getLastUpdate());

        return response;
    }

    // TODO: Update Blog
    @PutMapping("/blog/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreateBlogResponse updateBlog(@PathVariable String blogId, @RequestBody @Valid UpdateBlogRequest request) throws BlogNotFoundException {

        Blog updateBlog = theBlogService.updateBlog(blogId, request.getTitle(), request.getBody());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(updateBlog.getId());
        response.setBloggerId(updateBlog.getBlogger().getId());
        response.setCreatedAt(updateBlog.getCreatedAt());
        response.setLastUpdated(updateBlog.getLastUpdate());

        return response;
    }


    // TODO: Get Blog --> 200 OK
    @GetMapping("/blog/{blogId}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDetailsResponse getBlog(@PathVariable String blogId) throws BlogNotFoundException {
        Blog blog = theBlogService.getBlog(blogId);

        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBloggerId(blog.getBlogger().getId());
        blogDetailsResponse.setTitle(blog.getTitle());
        blogDetailsResponse.setBody(blog.getBody());
        blogDetailsResponse.setCreatedAt(blog.getCreatedAt());
        blogDetailsResponse.setLastUpdated(blog.getLastUpdate());

        System.out.println(blogDetailsResponse);
        return blogDetailsResponse;
    }


    // TODO: Get All Blogs --> 200 OK
    @GetMapping("/blog")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDetailsResponse> getAllBlog() {
        List<BlogDetailsResponse> blogDetailsResponseList = theBlogService.getAllBlog().stream()
                //the stream() method to iterate over the list of blogs.
                .map(blog -> {
                    BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
                    blogDetailsResponse.setBloggerId(blog.getBlogger().getId());
                    blogDetailsResponse.setTitle(blog.getTitle());
                    blogDetailsResponse.setBody(blog.getBody());
                    blogDetailsResponse.setCreatedAt(blog.getCreatedAt());
                    blogDetailsResponse.setLastUpdated(blog.getLastUpdate());
                    return blogDetailsResponse;
                    //The map() method then creates a new stream of BlogDetailsResponse objects from the stream of Blog objects.
                })
                .collect(Collectors.toList()); // the collect() method uses the Collectors.toList() method to collect the results of the map() operation into a list.
        return blogDetailsResponseList;
    }


    // TODO: Get All Blogs by Blogger
    @GetMapping("/blog/blogger/{bloggerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDetailsResponse> getAllBlogsByBloggerId(@PathVariable String bloggerId) throws BloggerNotFoundException {
        List<BlogDetailsResponse> blogDetailsResponseList = theBlogService.getAllBlogsByBloggerId(bloggerId).stream()
                .map(blog -> {
                    BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
                    blogDetailsResponse.setBloggerId(blog.getBlogger().getId());
                    blogDetailsResponse.setTitle(blog.getTitle());
                    blogDetailsResponse.setBody(blog.getBody());
                    blogDetailsResponse.setCreatedAt(blog.getCreatedAt());
                    blogDetailsResponse.setLastUpdated(blog.getLastUpdate());
                    return blogDetailsResponse;
                })
                .collect(Collectors.toList());
        return blogDetailsResponseList;
    }
}




