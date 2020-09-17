package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.dlyubanevich.config.Properties;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MessageLocalizationServiceTest {

    @Autowired
    MessageSource messageSource;

    @ParameterizedTest
    @MethodSource("getDataMessages")
    @DisplayName("должен возвращать корректные сообщения из бандла")
    void shouldGetCorrectMessages(String messageName, Properties properties, String expectedMessage) {
        MessageService messageService = new MessageLocalizationService(properties, messageSource);
        String message = messageService.getMessage(messageName);
        assertThat(message).isNotEmpty().isEqualTo(expectedMessage);
    }

    private static Stream<Arguments> getDataMessages(){

        Properties properties_en = new Properties();
        properties_en.setLocale(new Locale("en"));

        Properties properties_ru = new Properties();
        properties_ru.setLocale(new Locale("ru_RU"));

        return Stream.of(
                Arguments.of("message.greeting", properties_en, "Hello,my friend!"),
                Arguments.of("message.greeting", properties_ru, "Привет, дорогой!")
        );
    }
}