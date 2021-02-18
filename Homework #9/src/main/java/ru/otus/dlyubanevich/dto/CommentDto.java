package ru.otus.dlyubanevich.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.otus.dlyubanevich.domain.BookComment;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class CommentDto {

    private String id;
    private String bookId;
    private String text;

    public static CommentDto toDto(BookComment bookComment){
        return new CommentDto(bookComment.getId(), bookComment.getBook().getId(), bookComment.getText());
    }
}

