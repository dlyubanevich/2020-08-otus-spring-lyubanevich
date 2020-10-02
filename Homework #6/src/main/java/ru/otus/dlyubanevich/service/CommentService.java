package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Comment;

public interface CommentService {

    Comment save(Comment comment);
    Comment findById(long id);
    void delete(long id);

}
