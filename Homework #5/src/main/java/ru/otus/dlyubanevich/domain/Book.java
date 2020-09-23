package ru.otus.dlyubanevich.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {

    private long id;
    private final String name;
    private final Author author;
    private final Genre genre;

}
