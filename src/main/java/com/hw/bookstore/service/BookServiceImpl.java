package com.hw.bookstore.service;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.domain.repository.BookRepository;
import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.BookRequestDto;
import com.hw.bookstore.dto.BookSearchParamsRequestDto;
import com.hw.bookstore.exception.EntityNotFoundException;
import com.hw.bookstore.mapper.BookMapper;
import com.hw.bookstore.specification.BookSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookRequestDto requestDto) {
        Book book = bookMapper.toBook(null, requestDto);
        bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookMapper.toBookDtos(bookRepository.findAll());
    }

    @Override
    public List<BookDto> getAllBySearchParams(BookSearchParamsRequestDto requestDto) {
        BookSpecification bookSpecification = BookSpecification.of(requestDto);
        return bookMapper.toBookDtos(bookRepository.findAll(bookSpecification));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = getBook(id);
        return bookMapper.toBookDto(book);
    }

    @Override
    public BookDto updateById(Long id, BookRequestDto requestDto) {
        validateBookExistence(id);
        Book book = bookMapper.toBook(id, requestDto);
        bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    @Override
    public void deleteById(Long id) {
        validateBookExistence(id);
        bookRepository.deleteById(id);
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book was not found with id: '%s'"
                        .formatted(id)));
    }

    private void validateBookExistence(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book was not found with id: '%s'"
                    .formatted(id));
        }
    }
}
