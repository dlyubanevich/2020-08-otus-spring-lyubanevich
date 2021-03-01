package ru.otus.dlyubanevich.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Book;

import java.util.List;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Boolean> isExist(Book book) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(book.getName()));
        return mongoTemplate.find(query, Book.class)
                .collectList().map(foundedBooks -> matchBooksByAuthorsAndGenres(foundedBooks, book));
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
