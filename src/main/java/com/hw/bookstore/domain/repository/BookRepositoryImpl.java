package com.hw.bookstore.domain.repository;

import com.hw.bookstore.domain.entity.Book;
import com.hw.bookstore.exception.DataProcessingException;
import com.hw.bookstore.exception.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot save book: '%s' to DB".formatted(book), ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Book", Book.class)
                    .getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Cannot get list of books from DB", ex);
        }
    }

    @Override
    public Book findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Book where id = :id", Book.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException("Book was not found with id: '%s'"
                    .formatted(id), ex);
        } catch (Exception ex) {
            throw new DataProcessingException("Cannot get book with id: '%s' from DB"
                    .formatted(id), ex);
        }
    }
}
