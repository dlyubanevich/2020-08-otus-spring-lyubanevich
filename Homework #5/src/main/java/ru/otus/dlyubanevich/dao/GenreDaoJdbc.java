package ru.otus.dlyubanevich.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.mapper.GenreMapper;

import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final static GenreMapper GENRE_MAPPER = new GenreMapper();

    @Override
    public Genre save(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into genres (name) values (:name)",
                getSqlParametersWithName(genre.getName()),
                keyHolder
        );
        genre.setId(keyHolder.getKey().longValue());
        return genre;
    }

    @Override
    public List<Genre> getByName(String name) {
        return jdbc.query(
                "select id, name from genres where name = :name",
                getSqlParametersWithName(name),
                GENRE_MAPPER
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", GENRE_MAPPER);
    }

    private MapSqlParameterSource getSqlParametersWithName(String name){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        return parameters;
    }
}
