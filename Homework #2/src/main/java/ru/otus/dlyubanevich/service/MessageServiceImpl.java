package ru.otus.dlyubanevich.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final String greeting;
    private final String studentNameRequest;
    private final String questionInfo;
    private final String optionsInfo;
    private final String errorInfo;
    private final String appeal;
    private final String scoreInfo;
    private final String resultInfo;
    private final String indent;

    public MessageServiceImpl(@Value("${message.greeting}") String greeting,
                              @Value("${student.nameRequest}") String studentNameRequest,
                              @Value("${question.info}") String questionInfo,
                              @Value("${options.info}") String optionsInfo,
                              @Value("${error.info}") String errorInfo,
                              @Value("${student.appeal}") String appeal,
                              @Value("${score.info}") String scoreInfo,
                              @Value("${result.info}") String resultInfo,
                              @Value("${message.indent}") String indent) {
        this.greeting = greeting;
        this.studentNameRequest = studentNameRequest;
        this.questionInfo = questionInfo;
        this.optionsInfo = optionsInfo;
        this.errorInfo = errorInfo;
        this.appeal = appeal;
        this.scoreInfo = scoreInfo;
        this.resultInfo = resultInfo;
        this.indent = indent;
    }

    @Override
    public String getGreetingMessage() {
        return greeting;
    }

    @Override
    public String getStudentNameRequest() {
        return studentNameRequest;
    }

    @Override
    public String getQuestionInfo(int questionNumber) {
        return String.format(questionInfo, questionNumber);
    }

    @Override
    public String getOptionsInfo(int max) {
        return String.format(optionsInfo, max);
    }

    @Override
    public String getErrorInfo() {
        return errorInfo;
    }

    @Override
    public String getTestResult(String name, int score) {
        return String.format(appeal + scoreInfo + resultInfo, name, score);
    }

    @Override
    public String getIndent() {
        return indent;
    }
}
