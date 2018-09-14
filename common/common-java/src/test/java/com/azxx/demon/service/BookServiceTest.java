package com.azxx.demon.service;

import com.azxx.demon.SpringTestBase;
import com.azxx.demon.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest extends SpringTestBase {

    @Autowired
    private BookService bookService;

    @Test
    public void getBooks() {
        System.out.println(bookService);
        List<Book> books = bookService.getBooks();
        assertNotNull(books);
    }
}