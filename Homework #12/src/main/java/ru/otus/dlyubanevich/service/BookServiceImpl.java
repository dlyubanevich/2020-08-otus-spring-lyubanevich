package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookAlreadyExistException;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

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
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(()->{throw new BookNotFoundException("There is no book by id " + id);});
    }

    @Transactional
    @Override
    public void addAuthor(String id, Author author) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, null, author, null);
    }

    @Transactional
    @Override
    public void addGenre(String id, Genre genre) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, null, null, genre);
    }

    @Transactional
    @Override
    public void changeName(String id, String name) {
        updateAndSaveTheBookOrThrowBookNotFoundException(id, name, null, null);
    }

    private void updateAndSaveTheBookOrThrowBookNotFoundException(String id, String name, Author author, Genre genre){
        var optionalBook = bookRepository.findById(id);
        optionalBook.ifPresentOrElse(
                book -> updateAndSaveTheBook(book, name, author, genre),
                () -> {throw new BookNotFoundException("There is no book by id " + id);}
        );
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
