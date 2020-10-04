package ru.otus.dlyubanevich.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dlyubanevich.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Book save(Book book) {
        if (book.getId() == 0){
            entityManager.persist(book);
            return book;
        }else{
            return entityManager.merge(book);
        }
    }

    @Override
    public void delete(long id) {
        Query query = entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(
                entityManager.find(Book.class, id)
        );
    }

    @Override
    public boolean isExist(Book book) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.name = :name",
                Book.class
        );
        query.setParameter("name", book.getName());
        var foundedBooks = query.getResultList();
        return matchBooksByAuthorsAndGenres(foundedBooks, book);
    }

    private boolean matchBooksByAuthorsAndGenres(List<Book> foundedBooks, Book book) {
        boolean result = false;
        for (Book foundedBook : foundedBooks) {
            if (result) break;
            result = foundedBook.getAuthors().equals(book.getAuthors())
                    && foundedBook.getGenres().equals(book.getGenres());
        }
        return result;
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.name = :name",
                Book.class
        );
        query.setParameter("name", name);
        return query.getResultList();
    }
}
