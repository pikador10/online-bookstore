package com.hw.bookstore.service;

import com.hw.bookstore.dto.request.BookRequestDto;
import com.hw.bookstore.dto.request.BookSearchParamsRequestDto;
import com.hw.bookstore.dto.response.BookResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookResponseDto save(BookRequestDto requestDto);

    List<BookResponseDto> findAll();

    BookResponseDto findById(Long id);

    BookResponseDto updateById(Long id, BookRequestDto requestDto);

    void deleteById(Long id);

    Page<BookResponseDto> getAllBySearchParams(BookSearchParamsRequestDto requestDto,
                                               Pageable pageable
    );
}
