package ru.otus.dlyubanevich.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @DeleteMapping("/api/book/{id}")
    public void deleteBook(@PathVariable String id){
        libraryService.deleteBook(id);
    }

    @GetMapping("/api/book/{bookId}/comments")
    public List<CommentDto> getBookComments(@PathVariable String bookId){
        return libraryService.getBookComments(bookId);
    }

    @PostMapping("/api/book/{bookId}/comments")
    public CommentDto addComment(@PathVariable String bookId, @RequestBody CommentDto comment){
        return libraryService.addCommentToTheBook(bookId, comment.getText());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
