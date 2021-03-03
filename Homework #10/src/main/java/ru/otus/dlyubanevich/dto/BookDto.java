package ru.otus.dlyubanevich.dto;

import lombok.*;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {

    private String id;
    private String name;
    private String authors;
    private List<String> genres;

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
