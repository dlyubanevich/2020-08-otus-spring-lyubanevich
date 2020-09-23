package ru.otus.dlyubanevich.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dlyubanevich.service.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final LibraryService libraryService;

    @ShellMethod(value = "Show all books", key = {"books"})
    public void showAllBooks(){
        libraryService.getAllBooks().forEach(System.out::println);
    }

    @ShellMethod(value = "Add new book", key = {"save"})
    public void addBook(@ShellOption String name, @ShellOption String authorFirstName,
                        @ShellOption String authorLastName, @ShellOption String genre){
        libraryService.addBook(name, authorFirstName, authorLastName, genre);
    }

    @ShellMethod(value = "Delete book", key = {"delete"})
    public void deleteBook(@ShellOption long id){
        libraryService.deleteBook(id);
    }


    @ShellMethod(value = "Update book", key = {"update"})
    public void updateBook(@ShellOption long id, @ShellOption String name, @ShellOption String authorFirstName,
                           @ShellOption String authorLastName, @ShellOption String genre){
        libraryService.updateBook(id, name, authorFirstName, authorLastName, genre);
    }

    @ShellMethod(value = "Find books by name", key = {"fbb"})
    public void findBooksByName(@ShellOption String name){
        var books = libraryService.findBooksByName(name);
        if (books.size() != 0) {
            books.forEach(System.out::println);
        }else {
            System.out.println("There is no book with name " + name);
        }
    }
    @ShellMethod(value = "Find books by author", key = {"fba"})
    public void findBooksByAuthor(@ShellOption String authorFirstName, @ShellOption String authorLastName){
        var books = libraryService.findBooksByAuthor(authorFirstName, authorLastName);
        if (books.size() != 0) {
            books.forEach(System.out::println);
        }else {
            System.out.println("There is no book by author " + authorFirstName + " " + authorLastName);
        }
    }
    @ShellMethod(value = "Find books by genre", key = {"fbg"})
    public void findBooksByGenre(@ShellOption String genreName){
        var books = libraryService.findBooksByGenre(genreName);
        if (books.size() != 0) {
            books.forEach(System.out::println);
        }else {
            System.out.println("There is no book with genre " + genreName);
        }
    }

}
