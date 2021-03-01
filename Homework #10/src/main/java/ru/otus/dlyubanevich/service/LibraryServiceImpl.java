package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.BookComment;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.dto.CommentDto;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookService bookService;
    private final BookCommentService bookCommentService;

    @Transactional
    @Override
    public Mono<BookDto> saveBook(Book book) {
        return bookService.save(book).map(BookDto::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<BookDto> getAllBooks() {
        return bookService.getAll()
                .flatMap((book) -> Flux.just(BookDto.toDto(book)));
    }

    @Transactional
    @Override
    public Mono<Void> deleteBook(String id) {
        Mono<Void> deleteBookComments = bookCommentService.deleteAllByBookId(id);
        Mono<Void> deleteBook = bookService.delete(id);
        return Mono.zip(deleteBookComments, deleteBook)
            .then(Mono.empty());
    }

    @Transactional
    @Override
    public Mono<CommentDto> addCommentToTheBook(String bookId, String commentText) {
        if (commentText == null) {
            return Mono.error(new CommentNotFoundException("Comment text must be filled!"));
        }else {
            return bookCommentService.save(new BookComment(commentText, bookId))
                .map(CommentDto::toDto);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<CommentDto> getBookComments(String bookId) {
        return bookCommentService.findBookComments(bookId)
                .flatMap(bookComment -> Flux.just(CommentDto.toDto(bookComment)));
    }

}
