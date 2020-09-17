package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrintMessageByPropertyNameService implements IOMessageService {

    private final InputOutputService inputOutputService;
    private final MessageService messageService;

    @Override
    public void printMessage(String propertyName, String... args) {
        inputOutputService.print(
                messageService.getMessage(propertyName, args)
        );
    }

    @Override
    public String readAnswer() {
        return inputOutputService.scan();
    }

    @Override
    public void print(String message) {
        inputOutputService.println(message);
    }
}
