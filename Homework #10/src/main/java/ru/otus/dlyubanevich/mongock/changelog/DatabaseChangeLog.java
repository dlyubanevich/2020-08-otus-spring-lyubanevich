package ru.otus.dlyubanevich.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "dropDb", author = "dlyubanevich", runAlways = true)
    public void dropDb(MongockTemplate mongockTemplate) {
        mongockTemplate.getDb().drop();
    }


    @ChangeSet(order = "002", id = "insertBooks", author = "dlyubanevich", runAlways = true)
    public void insertBooks(BookRepository repository){

        List<Book> books = new ArrayList<>();
        books.add(new Book("Clean code", new Author("Robert", "Martin"), new Genre("Computer science")));
        books.add(new Book("The clean coder", new Author("Robert", "Martin"), new Genre("Computer science")));
        books.add(new Book("The Financier", new Author("Teodor", "Dreiser"), new Genre("Novel")));
        books.add(new Book("The Titan", new Author("Teodor", "Dreiser"), new Genre("Novel")));
        books.add(new Book("War and peace", new Author("Lev", "Tolstoy"), new Genre("Historical novel")));
        books.add(new Book("The Genius", new Author("Teodor", "Dreiser"), new Genre("Novel")));

        repository.saveAll(books).subscribe();

    }

}
