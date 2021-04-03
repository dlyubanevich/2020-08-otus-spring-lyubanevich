package ru.otus.dlyubanevich.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.domain.ControversialEpisode;
import ru.otus.dlyubanevich.domain.Decision;

@Service
public class VideoOperationRoomService{

    public Decision watchClosely(ControversialEpisode episode) {

        System.out.println("Watch episode " + episode.getName());

        try {
            Thread.sleep(RandomUtils.nextInt(1000, 3000));
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean isVideoAssistantAgree = RandomUtils.nextBoolean();
        if (isVideoAssistantAgree){
            return Decision.AFFIRMATIVE;
        }else{
            return Decision.NEGATIVE;
        }

    }

}
