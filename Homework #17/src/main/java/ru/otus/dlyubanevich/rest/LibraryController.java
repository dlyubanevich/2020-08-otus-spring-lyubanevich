package ru.otus.dlyubanevich.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.CommentDto;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @HystrixCommand(fallbackMethod = "getEmptyListOfBooks")
    @GetMapping("/api/book")
    public List<BookDto> getAllBooks(){
        return libraryService.getAllBooks();
    }

    public List<BookDto> getEmptyListOfBooks(){
        return new ArrayList<>();
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
    })
    @PostMapping("/api/book")
    public BookDto addBook(@RequestBody Book book){
        return BookDto.toDto(libraryService.saveBook(book));
    }

    @HystrixCommand()
    @DeleteMapping("/api/book/{id}")
    public void deleteBook(@PathVariable String id){
        libraryService.deleteBook(id);
    }

    @HystrixCommand()
    @GetMapping("/api/comments/{bookId}")
    public List<CommentDto> getBookComments(@PathVariable String bookId){
        return libraryService.getBookComments(bookId);
    }

    @HystrixCommand()
    @PostMapping("/api/comments")
    public CommentDto addComment(@RequestBody CommentDto comment){
        return libraryService.addCommentToTheBook(comment.getBookId(), comment.getText());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
