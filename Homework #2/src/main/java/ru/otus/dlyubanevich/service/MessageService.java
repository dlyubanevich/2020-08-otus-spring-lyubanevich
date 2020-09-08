package ru.otus.dlyubanevich.service;

public interface MessageService {

    String getGreetingMessage();
    String getStudentNameRequest();
    String getQuestionInfo(int questionNumber);
    String getOptionsInfo(int max);
    String getErrorInfo();
    String getTestResult(String name, int score);
    String getIndent();
}
