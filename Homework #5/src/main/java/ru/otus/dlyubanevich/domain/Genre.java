package ru.otus.dlyubanevich.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {

    private long id;
    private final String name;

}
