package ru.otus.dlyubanevich.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.dlyubanevich.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

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

}
