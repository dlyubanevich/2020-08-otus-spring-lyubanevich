package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.BookComment;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.dto.BookView;
import ru.otus.dlyubanevich.dto.CommentDto;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookService bookService;
    private final BookCommentService bookCommentService;

    @Transactional
    @Override
    public void addBook(String name, String authorFirstName, String authorLastName, String genreName) {
        var book = new Book(
                name,
                new Author(authorFirstName, authorLastName),
                new Genre(genreName)
        );
        bookService.save(book);
    }

    @Transactional
    @Override
    public Book saveBook(Book book) {
        return bookService.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBooks() {
        return bookService.getAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BookView findBookById(String id) {
        var book = bookService.findById(id);
        return new BookView(book.getId(), book.getName(), book.getAuthors(), book.getGenres());
    }

    @Transactional
    @Override
    public void deleteBook(String id) {
        bookCommentService.deleteAllByBookId(id);
        bookService.delete(id);
    }

    @Transactional
    @Override
    public void addInfoToTheBook(String bookId, String authorFirstName, String authorLastName, String genreName) {
        if (authorFirstName != null && authorLastName != null){
            bookService.addAuthor(bookId, new Author(authorFirstName, authorLastName));
        }
        if (genreName != null){
            bookService.addGenre(bookId, new Genre(genreName));
        }
    }

    @Transactional
    @Override
    public void changeTheNameOfTheBook(String bookId, String name) {
        bookService.changeName(bookId, name);
    }

    @Transactional
    @Override
    public CommentDto addCommentToTheBook(String bookId, String commentText) {
        if (commentText != null) {
            var bookComment = new BookComment(commentText, bookId);
            return CommentDto.toDto(bookCommentService.save(bookComment));
        }else{
            throw new CommentNotFoundException("Comment text must be filled!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getBookComments(String bookId) {
        return bookCommentService.findBookComments(bookId).stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

}
