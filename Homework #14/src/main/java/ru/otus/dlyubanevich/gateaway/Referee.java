package ru.otus.dlyubanevich.gateaway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.dlyubanevich.domain.ControversialEpisode;
import ru.otus.dlyubanevich.domain.Decision;

import java.util.Collection;

@MessagingGateway
public interface Referee {

    @Gateway(requestChannel = "refereeChannel", replyChannel = "assistantChannel")
    Collection<Decision> process(ControversialEpisode episode);

}
