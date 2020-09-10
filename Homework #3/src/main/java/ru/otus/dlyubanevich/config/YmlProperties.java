package ru.otus.dlyubanevich.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@ConfigurationProperties(prefix = "application")
public class YmlProperties {

    private String testingForm;
    private Locale locale;

}
