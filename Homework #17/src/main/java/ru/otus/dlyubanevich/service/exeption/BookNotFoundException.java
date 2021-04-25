package ru.otus.dlyubanevich.service.exeption;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message) {
        super(message);
    }

}
