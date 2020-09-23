package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.dao.BookDao;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookAlreadyExistException;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public void addBook(String name, Author author, Genre genre){
        var book = new Book(name, author, genre);
        if (bookIsExist(book)){
            throw new BookAlreadyExistException("Book with specified parameters is already exist!");
        }else{
            saveBook(book);
        }
    }

    private boolean bookIsExist(Book book) {
        return bookDao.isExist(book);
    }

    private void saveBook(Book book) {
        bookDao.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public void deleteBook(long id) {
        var book = findBookByIdOrThrowException(id);
        bookDao.delete(book);
    }

    @Override
    public void updateBook(long id, String name, Author author, Genre genre) {
        if (bookIsExist(id)){
            var book = new Book(name, author, genre);
            book.setId(id);
            bookDao.update(book);
        }else {
            throw new BookNotFoundException("There is no book by id " + id);
        }
    }

    @Override
    public List<Book> findBooksByOneOfAttributes(String name, Author author, Genre genre) {
        var book = new Book(name, author, genre);
        return bookDao.findBooksByOneOfAttributes(book);
    }

    private boolean bookIsExist(long id) {
        return bookDao.isExist(id);
    }

    private Book findBookByIdOrThrowException(long id) {
        Book book;
        try{
            book = bookDao.findById(id);
        }catch (EmptyResultDataAccessException exception){
            throw new BookNotFoundException("There is no book by id " + id);
        }
        return book;
    }

}
