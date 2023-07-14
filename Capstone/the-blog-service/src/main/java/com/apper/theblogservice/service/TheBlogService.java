package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.BlogNotFoundException;
import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheBlogService {

    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public TheBlogService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }

    // TODO: Register Blogger
    public Blogger registerBlogger(String email, String name, String password) throws EmailAlreadyRegisteredException {

        // TODO: Exception to make sure email is not yet registered.
        if (bloggerRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("Email already exist");
        }

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);

    }


    // TODO: Get Blogger
    public Blogger getBlogger(String id) throws BloggerNotFoundException {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);

        // TODO: If blogger id not found --> 404 NOT_FOUND
        if (bloggerResult.isEmpty()) {
            throw new BloggerNotFoundException("Blogger not found with ID: " + id);
        }

        return bloggerResult.get();
    }

    // TODO: Get All Blogger
    public List<Blogger> getAllBloggers() {
        return (List<Blogger>) bloggerRepository.findAll();
    }


    // TODO: createBlog
    public Blog createBlog(String title, String body, String bloggerId) throws BloggerNotFoundException {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(bloggerId);

        if (bloggerResult.isPresent()) {
            Blogger blogger = bloggerResult.get();

            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setBody(body);
            blog.setBlogger(blogger);

            return blogRepository.save(blog);
        } else {
            throw new BloggerNotFoundException("No Registered Blogger with id: " + bloggerId);
        }
    }

    // TODO: Update Blog
    public Blog updateBlog(String blogId, String title, String body) throws BlogNotFoundException{
        Blog blog = getBlog(blogId);

        blog.setTitle(title);
        blog.setBody(body);

        return blogRepository.save(blog);
    }


    // TODO: Get Blog
    public Blog getBlog(String blogId) throws BlogNotFoundException {
        Optional<Blog> blogResult = blogRepository.findById(blogId);

        // TODO: Exception for Blog ID --> 404 NOT_FOUND
        if (blogResult.isEmpty()) {
            throw new BlogNotFoundException("Blog not found with ID: " + blogId);
        }

        return blogResult.get();
    }


    // TODO: Get All Blogs
    public List<Blog> getAllBlog() {
        return (List<Blog>) blogRepository.findAll();
    }


    // TODO: Get All Blogs by Blogger
    public List<Blog> getAllBlogsByBloggerId(String bloggerId) throws BloggerNotFoundException {

        if (!bloggerRepository.existsById(bloggerId)) {
            throw new BloggerNotFoundException("No Registered Blogger with id: " + bloggerId);
        }
        Optional<Blogger> bloggerResult = bloggerRepository.findById(bloggerId);
        if (bloggerResult.isPresent()) {
            Blogger blogger = bloggerResult.get();
            return blogger.getBlogs();
        }
        return new ArrayList<>();
    }
}
