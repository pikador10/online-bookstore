package com.hw.bookstore.service;

import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.BookRequestDto;
import com.hw.bookstore.dto.BookSearchParamsRequestDto;
import java.util.List;

public interface BookService {

    BookDto save(BookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto updateById(Long id, BookRequestDto requestDto);

    void deleteById(Long id);

    List<BookDto> getAllBySearchParams(BookSearchParamsRequestDto requestDto);
}
