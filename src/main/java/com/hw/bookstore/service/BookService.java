package com.hw.bookstore.service;

import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {

    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
