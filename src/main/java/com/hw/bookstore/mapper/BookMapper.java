package com.hw.bookstore.mapper;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.BookRequestDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toBook(Long id, BookRequestDto requestDto);

    BookDto toBookDto(Book book);

    default List<BookDto> toBookDtos(List<Book> books) {
        return books.stream()
                .map(this::toBookDto)
                .toList();
    }
}
