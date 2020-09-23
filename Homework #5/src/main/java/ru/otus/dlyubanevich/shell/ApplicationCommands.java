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

    @ShellMethod(value = "Find by name", key = {"find"})
    public void findBooks(@ShellOption String name){
        var books = libraryService.findBookByName(name);
        if (books.size() != 0) {
            books.forEach(System.out::println);
        }else {
            System.out.println("There is no book with name " + name);
        }
    }

}
