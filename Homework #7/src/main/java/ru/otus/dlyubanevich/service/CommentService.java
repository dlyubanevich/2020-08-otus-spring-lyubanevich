package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);
    void saveByBookId(String text, long bookId);
    Comment findById(long id);
    void delete(long id);
    void deleteAllByBookId(long bookId);
    List<Comment> findBookComments(long bookId);

}
