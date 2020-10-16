package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.BookComment;

import java.util.List;

public interface BookCommentService {

    BookComment save(BookComment bookComment);
    BookComment findById(String id);
    void delete(String id);
    void deleteAllByBookId(String bookId);
    List<BookComment> findBookComments(String bookId);

}
