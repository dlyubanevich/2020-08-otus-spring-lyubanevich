package ru.otus.dlyubanevich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.dlyubanevich.config.Properties;
import ru.otus.dlyubanevich.service.TestingService;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class AwesomeTestApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(AwesomeTestApplication.class, args);
		TestingService testingService = context.getBean(TestingService.class);
		testingService.run();
	}

}
