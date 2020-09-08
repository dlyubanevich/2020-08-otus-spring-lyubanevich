package ru.otus.dlyubanevich.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dlyubanevich.dao.TaskDao;
import ru.otus.dlyubanevich.dao.TaskDaoCSV;

@Configuration
@PropertySource("classpath:test.properties")
public class TestingSpringConfig {

    @Value("${resource}")
    private String resource;

    @Bean
    TaskDao taskDao(){
        return new TaskDaoCSV(resource);
    }

}
