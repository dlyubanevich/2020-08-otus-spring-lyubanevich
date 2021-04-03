package ru.otus.dlyubanevich.shell;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dlyubanevich.domain.ControversialEpisode;
import ru.otus.dlyubanevich.domain.Decision;
import ru.otus.dlyubanevich.gateaway.Referee;

import java.util.Collection;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private static final String[] EPISODES = {
            "Goal for Team #1", "Goal for Team #2",
            "Penalty for Team #1", "Penalty for Team #2",
            "Red card for Team #1", "Red card for Team #2"
    };
    private static final int MATCH_TIME = 90;

    private final Referee referee;

    @ShellMethod(value = "Start football match", key = {"play"})
    public void startFootballMatch() throws Exception{

        int currentTime = 0;
        System.out.println("\nStart whistle");

        while (currentTime <= MATCH_TIME){

            currentTime += RandomUtils.nextInt(5, 20);
            Thread.sleep(RandomUtils.nextInt(1000, 3000));
            System.out.println("'" + currentTime);

            var episode = new ControversialEpisode(EPISODES[RandomUtils.nextInt( 0, EPISODES.length )]);
            Collection<Decision> decisions = referee.process(episode);
            System.out.println("VAR decision: " + decisions);

        }

        System.out.println("\nFinal whistle");

    }

}
