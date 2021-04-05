package ru.otus.dlyubanevich.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import ru.otus.dlyubanevich.service.VideoOperationRoomService;

@Configuration
@AllArgsConstructor
public class ChannelConfig {

    private final VideoOperationRoomService service;

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
                .handle(service, "watchClosely")
                .aggregate()
                .channel("assistantChannel")
                .get();
    }

}
