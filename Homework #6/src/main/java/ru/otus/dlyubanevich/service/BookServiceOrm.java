package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Comment;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookAlreadyExistException;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceOrm implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Book save(Book book){
        if (bookRepository.isExist(book)){
            throw new BookAlreadyExistException("Book with specified parameters is already exist!");
        }else{
            book = bookRepository.save(book);
        }
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Transactional
    @Override
    public void delete(long id) {
        bookRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(()->{throw new BookNotFoundException("There is no book by id " + id);});
    }

    @Transactional
    @Override
    public void addAuthor(long id, Author author) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, null, author, null, null);
    }

    @Transactional
    @Override
    public void addGenre(long id, Genre genre) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, null, null, genre, null);
    }

    @Transactional
    @Override
    public void changeName(long id, String name) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, name, null, null, null);
    }

    @Transactional
    @Override
    public void addComment(long id, Comment comment) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, null, null, null, comment);
    }

    private void updateAndSaveTheBookOrThrowBookNotFoundException(long id, String name, Author author, Genre genre, Comment comment){
        var optionalBook = bookRepository.findById(id);
        optionalBook.ifPresentOrElse(
                book -> updateAndSaveTheBook(book, name, author, genre, comment),
                () -> {throw new BookNotFoundException("There is no book by id " + id);}
        );
    }

    private void updateAndSaveTheBook(Book book, String name, Author author, Genre genre, Comment comment){
        if (name != null){
            book.setName(name);
        }
        if (author != null){
            book.getAuthors().add(author);
        }
        if (genre != null){
            book.getGenres().add(genre);
        }
        if (comment != null){
            book.getComments().add(comment);
        }
        bookRepository.save(book);
    }

}
