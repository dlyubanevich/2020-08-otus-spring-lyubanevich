package ru.otus.dlyubanevich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.dlyubanevich.config.Properties;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class AwesomeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwesomeTestApplication.class, args);
	}

}
