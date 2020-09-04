package ru.otus.dlyubanevich;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dlyubanevich.service.TestingService;

@ComponentScan
@PropertySource("classpath:application.properties")
@Configuration
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        TestingService testingService = context.getBean(TestingService.class);
        testingService.run();

        context.close();

    }

}
