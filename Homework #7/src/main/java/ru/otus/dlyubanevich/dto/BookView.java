package ru.otus.dlyubanevich.dto;

import lombok.Getter;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookView {

    private final long id;
    private final String name;
    private final List<String> authors;
    private final List<String> genres;

    public BookView(long id, String name, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.authors = authors.stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.toList());
        this.genres = genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getHeader() + "\n"
                + getAuthorsView() + "\n"
                + getGenresView()+ "\n";
    }

    private String getHeader() {
        return "Book '" + name + "'" + ", (id=" + id + ")";
    }

    private String getAuthorsView() {
        return authors.size() > 1 ? "Authors:" + authors : "Author:" + authors;
    }

    private String getGenresView() {
        return genres.size() > 1 ? "Genres:" + genres : "Genre:" + genres;
    }
}
