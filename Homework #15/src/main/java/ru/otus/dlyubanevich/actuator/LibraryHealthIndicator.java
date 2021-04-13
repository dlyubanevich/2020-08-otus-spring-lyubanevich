package ru.otus.dlyubanevich.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class LibraryHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;
    private final String uri;

    public LibraryHealthIndicator(RestTemplateBuilder restTemplateBuilder, @Value("${actuator.health.uri}") String uri) {
        this.restTemplate = restTemplateBuilder.build();
        this.uri = uri;
    }

    @Override
    public Health health() {
        int statusCode;
        HashMap<String, Object> details = new HashMap<>();
        try{
            ResponseEntity<Integer> response = restTemplate.getForEntity(uri, Integer.class);
            statusCode = response.getStatusCodeValue();
        }catch (RestClientResponseException exception){
            statusCode = exception.getRawStatusCode();
        }
        details.put("checking", uri);
        details.put("statusCode", statusCode);
        if (statusCode == 200){
           return Health.up()
                   .withDetails(details)
                   .build();
        }else{
            return Health.down()
                .withDetails(details)
                    .build();
        }
    }
}
