package com.hw.bookstore.specification;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.domain.entity.Book_;
import com.hw.bookstore.dto.BookSearchParamsRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor(staticName = "of")
public class BookSpecification implements Specification<Book> {

    private final BookSearchParamsRequestDto requestDto;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        var predicates = new ArrayList<Predicate>();

        if (requestDto.authors() != null && !requestDto.authors().isEmpty()) {
            predicates.add(root.get(Book_.AUTHOR).in(requestDto.authors()));
        }

        if (requestDto.titles() != null && !requestDto.titles().isEmpty()) {
            predicates.add(root.get(Book_.TITLE).in(requestDto.titles()));
        }

        if (requestDto.isbn() != null) {
            predicates.add(cb.equal(root.get(Book_.ISBN), requestDto.isbn()));
        }

        if (requestDto.lowerPrice() != null && requestDto.upperPrice() != null) {
            predicates.add(cb.between(
                    root.get(Book_.PRICE), requestDto.lowerPrice(), requestDto.upperPrice()));
        } else if (requestDto.lowerPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(
                    root.get(Book_.PRICE), requestDto.lowerPrice()));
        } else if (requestDto.upperPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(
                    root.get(Book_.PRICE), requestDto.upperPrice()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
