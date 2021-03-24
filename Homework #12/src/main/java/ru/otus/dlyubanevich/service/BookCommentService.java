package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.BookComment;

import java.util.List;

public interface BookCommentService {

    BookComment save(BookComment bookComment);
    void deleteAllByBookId(String bookId);
    List<BookComment> findBookComments(String bookId);

}
