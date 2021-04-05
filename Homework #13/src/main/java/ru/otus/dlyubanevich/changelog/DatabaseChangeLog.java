package ru.otus.dlyubanevich.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.dlyubanevich.domain.Statistic;

@ChangeLog
public class DatabaseChangeLog {

    private final static String SEASON = "2018/2019";
    private final static String LEAGUE = "Premier league";
    private final static int GAMES = 38;

    @ChangeSet(order = "001", id = "dropDb", author = "dlyubanevich", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "init", author = "dlyubanevich", runAlways = true)
    public void initPersons(MongockTemplate template){

        template.save(new Statistic(null, SEASON, LEAGUE, "Manchester City FC", 1, GAMES,98));
        template.save(new Statistic(null, SEASON, LEAGUE, "Liverpool FC", 2, GAMES,97));
        template.save(new Statistic(null, SEASON, LEAGUE, "Chelsea FC", 3, GAMES,72));
        template.save(new Statistic(null, SEASON, LEAGUE, "Tottenham Hotspur FC", 4, GAMES,71));
        template.save(new Statistic(null, SEASON, LEAGUE, "Arsenal FC", 5, GAMES,70));
        template.save(new Statistic(null, SEASON, LEAGUE, "Manchester United FC", 6, GAMES,66));
        template.save(new Statistic(null, SEASON, LEAGUE, "Wolverhampton Wanderers FC", 7, GAMES,57));
        template.save(new Statistic(null, SEASON, LEAGUE, "Everton FC", 8, GAMES,54));
        template.save(new Statistic(null, SEASON, LEAGUE, "Leicester City FC", 9, GAMES,52));
        template.save(new Statistic(null, SEASON, LEAGUE, "West Ham United FC", 10, GAMES,52));
        template.save(new Statistic(null, SEASON, LEAGUE, "Watford FC", 11, GAMES,50));
        template.save(new Statistic(null, SEASON, LEAGUE, "Crystal Palace FC", 12, GAMES,49));
        template.save(new Statistic(null, SEASON, LEAGUE, "Newcastle United FC", 13, GAMES,45));
        template.save(new Statistic(null, SEASON, LEAGUE, "AFC Bournemouth", 14, GAMES,45));
        template.save(new Statistic(null, SEASON, LEAGUE, "Burnley FC", 15, GAMES,40));
        template.save(new Statistic(null, SEASON, LEAGUE, "Southampton FC", 16, GAMES,39));
        template.save(new Statistic(null, SEASON, LEAGUE, "Brighton & Hove Albion FC", 17, GAMES,36));
        template.save(new Statistic(null, SEASON, LEAGUE, "Cardiff City FC", 18, GAMES,34));
        template.save(new Statistic(null, SEASON, LEAGUE, "Fulham FC", 19, GAMES,26));
        template.save(new Statistic(null, SEASON, LEAGUE, "Huddersfield Town FC", 20, GAMES,16));

    }
}
