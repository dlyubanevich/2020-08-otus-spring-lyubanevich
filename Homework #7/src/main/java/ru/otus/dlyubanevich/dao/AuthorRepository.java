package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

}
