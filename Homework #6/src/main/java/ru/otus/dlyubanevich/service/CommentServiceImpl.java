package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.CommentRepository;
import ru.otus.dlyubanevich.domain.Comment;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(()->{throw new CommentNotFoundException("There is no comment by id " + id);});
    }

    @Transactional
    @Override
    public void delete(long id) {
        commentRepository.delete(id);
    }
}
