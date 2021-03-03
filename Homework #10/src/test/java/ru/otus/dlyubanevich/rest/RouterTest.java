package ru.otus.dlyubanevich.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.service.LibraryService;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class RouterTest {

    private static final String BOOK_NAME = "War and peace";
    private static final Author AUTHOR = new Author("Lev", "Tolstoy");
    private static final Genre GENRE = new Genre("Historical novel");
    private static final String BOOK_ID = "1";

    @Autowired
    private RouterFunction<ServerResponse> libraryRoutes;

    @MockBean
    private LibraryService libraryService;

    @Test
    public void shouldSaveTheBookByPOST() {

        WebTestClient client = WebTestClient.bindToRouterFunction(libraryRoutes).build();

        var book = new Book(BOOK_ID, BOOK_NAME, AUTHOR, GENRE);
        var bookDto = BookDto.toDto(book);

        Mono<BookDto> monoBook = Mono.just(bookDto);
        given(libraryService.saveBook(book)).willReturn(monoBook);

        client.post()
                .uri("/api/book")
                .body(Mono.just(book), Book.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class).isEqualTo(bookDto);

    }

}
