package com.hw.bookstore.service;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.domain.repository.BookRepository;
import com.hw.bookstore.dto.request.BookRequestDto;
import com.hw.bookstore.dto.request.BookSearchParamsRequestDto;
import com.hw.bookstore.dto.response.BookResponseDto;
import com.hw.bookstore.exception.EntityNotFoundException;
import com.hw.bookstore.mapper.BookMapper;
import com.hw.bookstore.specification.BookSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto save(BookRequestDto requestDto) {
        Book book = bookMapper.toBook(null, requestDto);
        bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    @Override
    public List<BookResponseDto> findAll() {
        return bookMapper.toBookDtos(bookRepository.findAll());
    }

    @Override
    public Page<BookResponseDto> getAllBySearchParams(BookSearchParamsRequestDto requestDto,
                                                      Pageable pageable
    ) {
        return bookRepository.findAll(BookSpecification.of(requestDto), pageable)
                .map(bookMapper::toBookDto);
    }

    @Override
    public BookResponseDto findById(Long id) {
        Book book = getBook(id);
        return bookMapper.toBookDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto updateById(Long id, BookRequestDto requestDto) {
        validateBookExistence(id);
        Book book = bookMapper.toBook(id, requestDto);
        bookRepository.save(book);
        return bookMapper.toBookDto(book);
    }

    @Transactional
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
