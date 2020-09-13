package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrintMessageByPropertyNameService implements PrintMessageService {

    private final InputOutputService inputOutputService;
    private final MessageService messageService;

    @Override
    public void printMessage(String messageName, String... args) {
        inputOutputService.print(
                messageService.getMessage(messageName, args)
        );
    }
}
