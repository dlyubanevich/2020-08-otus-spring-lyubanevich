package ru.otus.dlyubanevich.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.mapper.AuthorMapper;

import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final static AuthorMapper AUTHOR_MAPPER = new AuthorMapper();

    @Override
    public Author save(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into authors (first_name, last_name) values (:first_name, :last_name)",
                getSqlParametersWithName(author.getFirstName(), author.getLastName()),
                keyHolder
        );
        author.setId(keyHolder.getKey().longValue());
        return author;
    }

    @Override
    public List<Author> getByName(String firstName, String lastName) {
        return jdbc.query(
                "select id, first_name, last_name from authors where first_name = :first_name and last_name = :last_name",
                getSqlParametersWithName(firstName, lastName),
                AUTHOR_MAPPER
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, first_name, last_name from authors", AUTHOR_MAPPER);
    }

    private MapSqlParameterSource getSqlParametersWithName(String firstName, String lastName){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("first_name", firstName);
        parameters.addValue("last_name", lastName);
        return parameters;
    }
}
