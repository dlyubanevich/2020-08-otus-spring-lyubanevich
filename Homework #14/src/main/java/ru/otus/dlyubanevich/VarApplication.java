package ru.otus.dlyubanevich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
public class VarApplication {

    @Bean
    public MessageChannel refereeChannel() {
        return new DirectChannel();
    }

    @Bean
    public PublishSubscribeChannel assistantChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 1 ).get();
    }

    @Bean
    public IntegrationFlow videoAssistantFlow() {
        return IntegrationFlows.from("refereeChannel")
                .split()
                .handle("videoOperationRoomService", "watchClosely")
                .aggregate()
                .channel("assistantChannel")
                .get();
    }

    public static void main(String[] args) {
        SpringApplication.run(VarApplication.class, args);
    }

}
