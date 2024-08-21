package com.hw.bookstore.mapper;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.BookRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toBook(Long id, BookRequestDto requestDto);

    BookDto toBookDto(Book book);
}
