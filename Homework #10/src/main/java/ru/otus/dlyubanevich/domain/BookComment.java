package ru.otus.dlyubanevich.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books_comments")
public class BookComment {

    @Id
    private String id;
    private String text;
    @DBRef
    private Book book;

    public BookComment(String id, String text, String bookId) {
        this(text, bookId);
        this.id = id;
    }

    public BookComment(String text, String bookId) {
        this.text = text;
        this.book = new Book(bookId, "", Collections.emptySet(), Collections.emptyList());
    }

}
