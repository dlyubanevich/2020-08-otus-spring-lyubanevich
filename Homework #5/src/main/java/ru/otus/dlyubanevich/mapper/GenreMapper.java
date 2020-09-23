package ru.otus.dlyubanevich.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.dlyubanevich.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        var name = resultSet.getString("name");
        var genre = new Genre(name);
        genre.setId(id);
        return genre;
    }
}