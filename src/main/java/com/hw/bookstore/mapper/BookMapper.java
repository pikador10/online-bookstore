package com.hw.bookstore.mapper;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.CreateBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book toBook(CreateBookRequestDto requestDto);

    BookDto toBookDto(Book book);
}
