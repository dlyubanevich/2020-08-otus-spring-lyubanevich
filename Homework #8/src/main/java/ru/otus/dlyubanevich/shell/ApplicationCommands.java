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

    @ShellMethod(value = "Show book", key = {"book"})
    public void showBookById(@ShellOption(value = {"-id", "--id"}) String id){
        var bookView = libraryService.findBookById(id);
        System.out.println(bookView);
    }

    @ShellMethod(value = "Add new book", key = {"save"})
    public void addBook(@ShellOption(value = {"-n"}) String name,
                        @ShellOption(value = {"-fa"}) String authorFirstName,
                        @ShellOption(value = {"-fl"}) String authorLastName,
                        @ShellOption(value = {"-g"}) String genre){
        libraryService.addBook(name, authorFirstName, authorLastName, genre);
    }

    @ShellMethod(value = "Change name of the book", key = {"change-name"})
    public void changeName(@ShellOption(value = {"-id"}) String bookId, @ShellOption(value = {"-n"}) String name){
        libraryService.changeTheNameOfTheBook(bookId, name);
    }

    @ShellMethod(value = "Delete book", key = {"delete"})
    public void deleteBook(@ShellOption(value = {"-id"}) String bookId){
        libraryService.deleteBook(bookId);
    }

    @ShellMethod(value = "Add author to the book", key = {"add-author"})
    public void addAuthorToTheBook(@ShellOption(value = {"-id"}) String bookId,
                           @ShellOption(value = {"-fa"}) String authorFirstName,
                           @ShellOption(value = {"-fl"}) String authorLastName){
        libraryService.addInfoToTheBook(bookId, authorFirstName, authorLastName, null);
    }

    @ShellMethod(value = "Add genre to the book", key = {"add-genre"})
    public void addGenreToTheBook(@ShellOption(value = {"-id"}) String bookId,
                           @ShellOption(value = {"-g"}) String genre){
        libraryService.addInfoToTheBook(bookId, null, null, genre);
    }

    @ShellMethod(value = "Add author and genre to the book", key = {"add-info"})
    public void addAuthorAndGenreToTheBook(@ShellOption(value = {"-id"}) String bookId,
                           @ShellOption(value = {"-fa"}) String authorFirstName,
                           @ShellOption(value = {"-fl"}) String authorLastName,
                           @ShellOption(value = {"-g"}) String genre){
        libraryService.addInfoToTheBook(bookId, authorFirstName, authorLastName, genre);
    }

    @ShellMethod(value = "Add comment", key = {"add-comment"})
    public void addCommentToTheBook(@ShellOption(value = {"-id"}) String bookId,
                           @ShellOption(value = {"-c"}) String commentText){
        libraryService.addCommentToTheBook(bookId, commentText);
    }

    @ShellMethod(value = "Show comment's", key = {"comments"})
    public void showComments(@ShellOption(value = {"-id"}) String bookId){
        var comments = libraryService.getBookComments(bookId);
        System.out.println(comments);
    }

}
