package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.dlyubanevich.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.book.id = :bookId")
    List<Comment> findByBookId(@Param("bookId") long bookId);

    @Modifying
    @Query(value = "insert into books_comments (id, text, book_id) values (null, :text, :bookId)", nativeQuery = true)
    void save(@Param("text") String text, @Param("bookId") long bookId);

    @Modifying
    @Query(value = "delete from books_comments where book_id = :bookId", nativeQuery = true)
    void deleteAllByBookId(@Param("bookId") long bookId);

}
