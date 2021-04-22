package ru.otus.dlyubanevich.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.dlyubanevich.dao.BookCommentRepository;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.BookComment;
import ru.otus.dlyubanevich.domain.Genre;

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
        books.add(new Book("1", "Clean code", new Author("Robert", "Martin"), new Genre("Computer science")));
        books.add(new Book("2", "The clean coder", new Author("Robert", "Martin"), new Genre("Computer science")));
        books.add(new Book("3", "The Financier", new Author("Teodor", "Dreiser"), new Genre("Novel")));
        books.add(new Book("4", "The Titan", new Author("Teodor", "Dreiser"), new Genre("Novel")));
        books.add(new Book("5", "War and peace", new Author("Lev", "Tolstoy"), new Genre("Historical novel")));
        books.add(new Book("6", "The Genius", new Author("Teodor", "Dreiser"), new Genre("Novel")));

        repository.saveAll(books);

    }

    @ChangeSet(order = "003", id = "insertBookComments", author = "dlyubanevich", runAlways = true)
    public void insertBookComments(BookCommentRepository repository, BookRepository bookRepository){

        String bookId = "1";

        List<BookComment> comments = new ArrayList<>();
        comments.add(new BookComment("1", "Best book ever", bookId));
        comments.add(new BookComment("2", "Great!", bookId));

        repository.saveAll(comments);

    }

}
