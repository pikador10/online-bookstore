package com.hw.bookstore.mapper;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.dto.request.BookRequestDto;
import com.hw.bookstore.dto.response.BookResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "deleted", ignore = true)
    Book toBook(Long id, BookRequestDto requestDto);

    BookResponseDto toBookDto(Book book);

    default List<BookResponseDto> toBookDtos(List<Book> books) {
        return books.stream()
                .map(this::toBookDto)
                .toList();
    }
}
