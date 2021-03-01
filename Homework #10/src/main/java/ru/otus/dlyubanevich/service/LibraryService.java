package ru.otus.dlyubanevich.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.dto.CommentDto;

public interface LibraryService {

    Mono<BookDto> saveBook(Book book);
    Mono<CommentDto> addCommentToTheBook(String bookId, String commentText);
    Mono<Void> deleteBook(String id);
    Flux<BookDto> getAllBooks();
    Flux<CommentDto> getBookComments(String bookId);

}
