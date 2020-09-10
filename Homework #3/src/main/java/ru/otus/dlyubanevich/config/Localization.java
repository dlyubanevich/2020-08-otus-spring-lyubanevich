package ru.otus.dlyubanevich.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class Localization {

    @Bean
    public MessageSource messageSource(){
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/messages");
        ms.setDefaultEncoding("windows-1251");
        return ms;
    }

}
