package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Book;

public interface BookRepositoryCustom {

    boolean isExist(Book book);

}
