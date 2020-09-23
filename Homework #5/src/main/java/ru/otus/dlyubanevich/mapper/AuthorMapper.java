package ru.otus.dlyubanevich.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.dlyubanevich.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        var firstName = resultSet.getString("first_name");
        var lastName = resultSet.getString("last_name");
        var author = new Author(firstName, lastName);
        author.setId(id);
        return author;
    }
}