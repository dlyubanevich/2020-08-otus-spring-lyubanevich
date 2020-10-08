package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    List<Book> findByName(String name);

}
