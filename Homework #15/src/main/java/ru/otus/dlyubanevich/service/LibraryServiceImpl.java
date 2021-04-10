package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.BookComment;
import ru.otus.dlyubanevich.dto.BookDto;
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

    @Transactional
    @Override
    public void deleteBook(String id) {
        bookCommentService.deleteAllByBookId(id);
        bookService.delete(id);
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

    @Override
    public int getBookQuantity() {
        return bookService.getBookQuantity();
    }
}
