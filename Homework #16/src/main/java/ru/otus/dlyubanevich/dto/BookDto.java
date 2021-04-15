package ru.otus.dlyubanevich.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class BookDto {

    private final String id;
    private final String name;
    private final String authors;
    private final List<String> genres;

    public static BookDto toDto(Book book){
        var authors = book.getAuthors().stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.joining(","));
        var genres = book.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        return new BookDto(book.getId(), book.getName(), authors, genres);
    }

}
