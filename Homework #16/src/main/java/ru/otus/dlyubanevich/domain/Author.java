package ru.otus.dlyubanevich.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

    private final String firstName;
    private final String lastName;

}
