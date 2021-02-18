package ru.otus.dlyubanevich.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String name;
    private Set<Author> authors;
    private List<Genre> genres;

    public Book(String id, String name, Author author, Genre genre) {
        this(name, author, genre);
        this.id = id;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.authors = Collections.singleton(author);
        this.genres = Collections.singletonList(genre);
    }

}
