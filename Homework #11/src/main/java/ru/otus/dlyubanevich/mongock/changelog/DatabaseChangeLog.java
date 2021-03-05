package ru.otus.dlyubanevich.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.dao.UserDetailRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.domain.User;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "dropDb", author = "dlyubanevich", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
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

        repository.saveAll(books);

    }

    @ChangeSet(order = "003", id = "insertUsers", author = "dlyubanevich", runAlways = true)
    public void insertUsers(UserDetailRepository repository, PasswordEncoder passwordEncoder){

        List<User> users = new ArrayList<>();
        users.add(new User(null, "admin", passwordEncoder.encode("111")));
        users.add(new User(null, "user", passwordEncoder.encode("111")));

        repository.saveAll(users);

    }
}
