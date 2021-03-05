package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Mono<Book> save(final Book book){
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Mono<Void> delete(String id) {
        return bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public Mono<Book> addAuthor(String id, Author author) {
        return Mono.from(
            updateAndSaveTheBookOrThrowBookNotFoundException(id, null, author, null)
        );
    }

    @Transactional
    @Override
    public Mono<Book> addGenre(String id, Genre genre) {
        return Mono.from(
            updateAndSaveTheBookOrThrowBookNotFoundException(id, null, null, genre)
        );
    }

    @Transactional
    @Override
    public Mono<Book> changeName(String id, String name) {
        return Mono.from(
            updateAndSaveTheBookOrThrowBookNotFoundException(id, name, null, null)
        );
    }

    private Mono<Book> updateAndSaveTheBookOrThrowBookNotFoundException(String id, String name, Author author, Genre genre){
        return Mono.from(bookRepository.findById(id))
            .switchIfEmpty(Mono.error(new BookNotFoundException("There is no book by id " + id)))
            .doOnSuccess((book) -> updateAndSaveTheBook(book, name, author, genre));
    }

    private void updateAndSaveTheBook(Book book, String name, Author author, Genre genre){
        if (name != null){
            book.setName(name);
        }
        if (author != null){
            book.getAuthors().add(author);
        }
        if (genre != null){
            book.getGenres().add(genre);
        }
        bookRepository.save(book);
    }
}
