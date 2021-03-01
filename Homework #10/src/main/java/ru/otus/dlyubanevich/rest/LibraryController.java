package ru.otus.dlyubanevich.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.CommentDto;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.service.LibraryService;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/api/book")
    public Flux<BookDto> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @PostMapping("/api/book")
    public Mono<BookDto> addBook(@RequestBody Book book){
        return libraryService.saveBook(book);
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> deleteBook(@PathVariable String id){
        return libraryService.deleteBook(id);
    }

    @GetMapping("/api/comments/{bookId}")
    public Flux<CommentDto> getBookComments(@PathVariable String bookId){
        return libraryService.getBookComments(bookId);
    }

    @PostMapping("/api/comments")
    public Mono<CommentDto> addComment(@RequestBody CommentDto comment){
        return libraryService.addCommentToTheBook(comment.getBookId(), comment.getText());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
