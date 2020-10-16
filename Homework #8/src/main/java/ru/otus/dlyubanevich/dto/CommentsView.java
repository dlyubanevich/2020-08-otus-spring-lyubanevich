package ru.otus.dlyubanevich.dto;

import ru.otus.dlyubanevich.domain.BookComment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentsView {

    private final String bookName;
    private final List<String> comments;

    public CommentsView(String bookName, List<BookComment> bookComments) {
        this.bookName = bookName;
        this.comments = bookComments.stream()
                .map(BookComment::getText)
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
