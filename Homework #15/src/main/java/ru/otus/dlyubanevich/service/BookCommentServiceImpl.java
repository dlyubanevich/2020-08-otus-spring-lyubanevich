package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.BookCommentRepository;
import ru.otus.dlyubanevich.domain.BookComment;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;

    @Transactional
    @Override
    public BookComment save(BookComment bookComment) {
        return bookCommentRepository.save(bookComment);
    }

    @Transactional
    @Override
    public void deleteAllByBookId(String bookId) {
        bookCommentRepository.deleteAllByBookId(bookId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookComment> findBookComments(String bookId) {
        return bookCommentRepository.findByBookId(bookId);
    }
}
