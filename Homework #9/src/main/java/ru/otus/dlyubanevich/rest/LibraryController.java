package ru.otus.dlyubanevich.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.CommentDto;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/api/book")
    public List<BookDto> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @PostMapping("/api/book")
    public BookDto addBook(@RequestBody Book book){
        return BookDto.toDto(libraryService.saveBook(book));
    }

    @DeleteMapping("/api/book")
    public void deleteBook(String bookId){
        libraryService.deleteBook(bookId);
    }

    @GetMapping("/api/comments")
    public List<CommentDto> getBookComments(String bookId){
        return libraryService.getBookComments(bookId);
    }

    @PostMapping("/api/comments")
    public CommentDto addComment(@RequestBody CommentDto comment){
        return libraryService.addCommentToTheBook(comment.getBookId(), comment.getText());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
