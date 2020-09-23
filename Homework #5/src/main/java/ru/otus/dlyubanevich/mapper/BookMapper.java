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
        long author_id = resultSet.getLong("author_id");
        var author_first_name = resultSet.getString("author_first_name");
        var author_last_name = resultSet.getString("author_last_name");
        long genre_id = resultSet.getLong("genre_id");
        var genre_name = resultSet.getString("genre_name");

        var author = new Author(author_first_name, author_last_name);
        author.setId(author_id);

        var genre = new Genre(genre_name);
        genre.setId(genre_id);

        var book = new Book(name, author, genre);
        book.setId(id);

        return book;
    }

}
