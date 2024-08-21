package com.hw.bookstore.validation;

import com.hw.bookstore.domain.repository.BookRepository;
import com.hw.bookstore.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookValidation {

    private final BookRepository bookRepository;

    public void validateBookExistence(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book was not found with id: '%s'"
                    .formatted(id));
        }
    }
}
