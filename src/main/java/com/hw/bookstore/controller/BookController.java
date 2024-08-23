package com.hw.bookstore.controller;

import com.hw.bookstore.dto.BookDto;
import com.hw.bookstore.dto.BookRequestDto;
import com.hw.bookstore.dto.BookSearchParamsRequestDto;
import com.hw.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API:BOOK")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @Operation(summary = "Get all books by search params with pagination and sorting")
    @GetMapping("/search")
    public Page<BookDto> getAllBySearchParams(@RequestBody BookSearchParamsRequestDto requestDto,
                                              Pageable pageable
    ) {
        return bookService.getAllBySearchParams(requestDto, pageable);
    }

    @Operation(summary = "Get book by id")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @Operation(summary = "Create new book")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@RequestBody @Valid BookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @Operation(summary = "Update book by id")
    @PutMapping("/{id}")
    public BookDto updateBookById(@PathVariable Long id,
                                  @RequestBody BookRequestDto requestDto
    ) {
        return bookService.updateById(id, requestDto);
    }

    @Operation(summary = "Delete book by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
