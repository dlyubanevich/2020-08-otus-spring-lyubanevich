package ru.otus.dlyubanevich.service.exeption;

public class BookAlreadyExistException extends RuntimeException{

    public BookAlreadyExistException(String message) {
        super(message);
    }
}
