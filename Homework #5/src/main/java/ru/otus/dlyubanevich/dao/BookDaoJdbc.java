package ru.otus.dlyubanevich.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.mapper.BookMapper;

import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final static BookMapper BOOK_MAPPER = new BookMapper();

    @Override
    public Book save(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                getBookSqlParameters(book),
                keyHolder
        );
        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    @Override
    public void delete(Book book) {
        var parameters = new MapSqlParameterSource();
        parameters.addValue("id", book.getId());
        jdbc.update(
                "delete from books where id = :id",
                parameters
        );
    }

    @Override
    public void update(Book book) {
        jdbc.update(
                "update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id",
                getBookSqlParameters(book)
        );
    }

    @Override
    public List<Book> getAll() {
        var query = "select books.id, books.name, books.author_id, books.genre_id, authors.first_name as author_first_name, " +
                "authors.last_name as author_last_name, genres.name as genre_name " +
                "from books as books " +
                    "left join authors as authors on books.author_id = authors.id " +
                    "left join genres as genres on books.genre_id = genres.id";
        return jdbc.query(query, BOOK_MAPPER);
    }

    @Override
    public Book findById(long id) {
        var query = "select books.id, books.name, books.author_id, books.genre_id, authors.first_name as author_first_name, " +
                "authors.last_name as author_last_name, genres.name as genre_name " +
                "from books as books " +
                    "left join authors as authors on books.author_id = authors.id " +
                    "left join genres as genres on books.genre_id = genres.id " +
                "where books.id = :id";
        var parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        return jdbc.queryForObject(query, parameters, BOOK_MAPPER);
    }

    @Override
    public List<Book> findBooksByOneOfAttributes(Book book) {
        var query = "select books.id, books.name, books.author_id, books.genre_id, authors.first_name as author_first_name, " +
                "authors.last_name as author_last_name, genres.name as genre_name " +
                "from books as books " +
                "left join authors as authors on books.author_id = authors.id " +
                "left join genres as genres on books.genre_id = genres.id " +
                "where (books.name = :name or (authors.first_name = :first_name and authors.last_name = :last_name) or genres.name = :genre_name)";
        return jdbc.query(
                query,
                getBookSqlAttributes(book),
                BOOK_MAPPER);
    }

    @Override
    public boolean isExist(Book book) {
        var query = "select count(*) from books where (name = :name and author_id = :author_id and genre_id = :genre_id)";
        var count = jdbc.queryForObject(
                query,
                getBookSqlParameters(book),
                Integer.class);
        return count != 0;
    }

    @Override
    public boolean isExist(long id) {
        var query = "select count(*) from books where id = :id";
        var parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        var count = jdbc.queryForObject(
                query,
                parameters,
                Integer.class);
        return count != 0;
    }

    private MapSqlParameterSource getBookSqlParameters(Book book) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", book.getId());
        parameters.addValue("name", book.getName());
        parameters.addValue("author_id", book.getAuthor().getId());
        parameters.addValue("genre_id", book.getGenre().getId());
        return parameters;
    }

    private MapSqlParameterSource getBookSqlAttributes(Book book) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", book.getName());
        parameters.addValue("first_name", book.getAuthor().getFirstName());
        parameters.addValue("last_name", book.getAuthor().getLastName());
        parameters.addValue("genre_name", book.getGenre().getName());
        return parameters;
    }
}
