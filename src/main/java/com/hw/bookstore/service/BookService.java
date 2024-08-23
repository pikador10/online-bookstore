package com.hw.bookstore.service;

import com.hw.bookstore.domain.entity.Book;
import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();
}
