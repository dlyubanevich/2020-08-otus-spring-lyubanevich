package ru.otus.dlyubanevich.service;

public interface IOMessageService {

    void printMessage(String messageName, String... args);
    void print(String message);
    String readAnswer();

}
