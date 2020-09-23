package ru.otus.dlyubanevich.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        var name = resultSet.getString("name");
        long authorId = resultSet.getLong("author_id");
        var authorFirstName = resultSet.getString("author_first_name");
        var authorLastName = resultSet.getString("author_last_name");
        long genreId = resultSet.getLong("genre_id");
        var genreName = resultSet.getString("genre_name");

        var author = new Author(authorFirstName, authorLastName);
        author.setId(authorId);

        var genre = new Genre(genreName);
        genre.setId(genreId);

        var book = new Book(name, author, genre);
        book.setId(id);

        return book;
    }

}
