package ru.otus.dlyubanevich.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dlyubanevich.dao.TaskDao;
import ru.otus.dlyubanevich.dao.TaskDaoImpl;

@Configuration
@PropertySource("classpath:test.properties")
public class TestingSpringConfig {

    @Bean
    TaskDao taskDao(){
        return new TaskDaoImpl();
    }

}
