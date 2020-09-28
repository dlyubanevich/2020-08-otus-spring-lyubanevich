package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.config.Properties;

@Service
@RequiredArgsConstructor
public class MessageLocalizationService implements MessageService {

    private final Properties properties;
    private final MessageSource messageSource;

    @Override
    public String getMessage(String messageName, String... args) {
        return messageSource.getMessage(messageName, args, properties.getLocale());
    }

}
