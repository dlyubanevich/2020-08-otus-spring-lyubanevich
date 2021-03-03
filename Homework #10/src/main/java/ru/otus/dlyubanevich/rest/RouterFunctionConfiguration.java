package ru.otus.dlyubanevich.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.dto.CommentDto;
import ru.otus.dlyubanevich.service.LibraryService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterFunctionConfiguration {

    @Bean
    public RouterFunction<ServerResponse> libraryRoutes(LibraryService libraryService){
        return route()
                .GET(
                        "/api/book",
                        request -> ok().body(libraryService.getAllBooks(), BookDto.class)
                )
                .POST(
                        "/api/book",
                        accept(APPLICATION_JSON),
                        request -> request.bodyToMono(Book.class)
                                .flatMap(libraryService::saveBook)
                                .flatMap(bookDto -> ok().body(fromValue(bookDto)))
                )
                .DELETE(
                        "/api/book/{id}",
                        request -> libraryService.deleteBook(request.pathVariable("id"))
                                .flatMap(removed -> ok().build())
                )
                .GET(
                        "/api/book/{bookId}/comments",
                        request -> ok().body(libraryService.getBookComments(request.pathVariable("bookId")), CommentDto.class)
                )
                .POST(
                        "/api/book/{bookId}/comments",
                        accept(APPLICATION_JSON),
                        request -> request.bodyToMono(CommentDto.class)
                                .flatMap(comment -> libraryService.addCommentToTheBook(request.pathVariable("bookId"), comment.getText()))
                                .flatMap(commentDto -> ok().body(fromValue(commentDto)))
                )
                .build();
    }
}
