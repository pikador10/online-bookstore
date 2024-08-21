package com.hw.bookstore.service;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.domain.repository.BookRepository;
import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.BookRequestDto;
import com.hw.bookstore.exception.BookNotFoundException;
import com.hw.bookstore.mapper.BookMapper;
import com.hw.bookstore.validation.BookValidation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookValidation bookValidation;

    @Override
    public BookDto save(BookRequestDto requestDto) {
        Book book = bookMapper.toBook(null, requestDto);
        bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = getBook(id);
        return bookMapper.toBookDto(book);
    }

    @Override
    public BookDto updateById(Long id, BookRequestDto requestDto) {
        bookValidation.validateBookExistence(id);
        Book book = bookMapper.toBook(id, requestDto);
        bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookValidation.validateBookExistence(id);
        bookRepository.deleteById(id);
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book was not found with id: '%s'"
                        .formatted(id)));
    }
}
