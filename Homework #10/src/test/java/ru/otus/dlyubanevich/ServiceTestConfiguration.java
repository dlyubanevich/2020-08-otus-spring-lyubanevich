package ru.otus.dlyubanevich;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@EnableWebFlux
@EnableMongock
@TestConfiguration
@ComponentScan("ru.otus.dlyubanevich.service")
public class ServiceTestConfiguration {


}
