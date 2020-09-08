package ru.otus.dlyubanevich;

import org.springframework.context.annotation.*;
import ru.otus.dlyubanevich.service.TestingService;

@ComponentScan
@PropertySources({@PropertySource("classpath:application.properties"), @PropertySource("classpath:messages.properties")})
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
