package com.gcash.service.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    private final IdGenerator idGenerator;

    public String createBook(String title) {
        this.idGenerator = idGenerator;

        Book book = new Book();
        book.setId(idGenerator.nextId());
        book.setTitle(title);

        books.add(book);
    }
}
