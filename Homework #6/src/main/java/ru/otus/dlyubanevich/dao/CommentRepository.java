package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    void delete(long id);

}
