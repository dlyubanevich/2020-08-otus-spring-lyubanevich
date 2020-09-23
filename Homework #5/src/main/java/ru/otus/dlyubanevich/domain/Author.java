package ru.otus.dlyubanevich.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {

    private long id;
    private final String firstName;
    private final String lastName;

}
