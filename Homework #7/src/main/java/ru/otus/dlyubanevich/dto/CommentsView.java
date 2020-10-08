package ru.otus.dlyubanevich.dto;

import ru.otus.dlyubanevich.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentsView {

    private final String bookName;
    private final List<String> comments;

    public CommentsView(String bookName, List<Comment> comments) {
        this.bookName = bookName;
        this.comments = comments.stream()
                .map(Comment::getText)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getHeader() + "\n"
            + getCommentsOfTheBook() + "\n";
    }

    private String getHeader() {
        return "Comments to the book '" + bookName + "':";
    }

    private String getCommentsOfTheBook() {
        return comments.toString();
    }
}
