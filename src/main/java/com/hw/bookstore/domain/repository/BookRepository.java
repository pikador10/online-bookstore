package com.hw.bookstore.domain.repository;

import com.hw.bookstore.domain.entity.Book;
import java.util.List;

public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();
}
