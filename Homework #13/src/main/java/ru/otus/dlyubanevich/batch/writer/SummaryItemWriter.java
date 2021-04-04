package ru.otus.dlyubanevich.batch.writer;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;
import ru.otus.dlyubanevich.domain.Summary;
import ru.otus.dlyubanevich.service.SummaryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
@AllArgsConstructor
public class SummaryItemWriter implements ItemWriter<Summary> {

    private final SummaryService summaryService;
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void write(List<? extends Summary> items) {
        EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
        if (entityManager == null) {
            throw new DataAccessResourceFailureException("Unable to obtain a transactional EntityManager");
        }
        for (Summary item : items) {
            save(item, entityManager);
        }
        entityManager.flush();
    }

    private void save(Summary item, EntityManager entityManager){
        loadOrSaveLeague(item, entityManager);
        loadOrSaveSeason(item, entityManager);
        loadOrSaveTeam(item, entityManager);
        saveSummary(item, entityManager);
    }

    private void loadOrSaveLeague(Summary item, EntityManager entityManager) {
        var league = item.getLeague();
        var savedLeague = summaryService.findByName(league);
        if (savedLeague == null) {
            if (!entityManager.contains(league)) {
                entityManager.persist(league);
            }
        }else {
            item.setLeague(savedLeague);
        }
    }

    private void loadOrSaveSeason(Summary item, EntityManager entityManager) {
        var season = item.getSeason();
        if (!entityManager.contains(season)) {
            var savedSeason = summaryService.findByName(season);
            if (savedSeason == null) {
                entityManager.persist(season);
            }else{
                item.setSeason(savedSeason);
            }
        }
    }

    private void loadOrSaveTeam(Summary item, EntityManager entityManager) {
        var team = item.getTeam();
        if (!entityManager.contains(team)) {
            var savedTeam = summaryService.findByName(team);
            if (savedTeam == null){
                entityManager.persist(team);
            }else {
                item.setTeam(savedTeam);
            }
        }
    }

    private void saveSummary(Summary item, EntityManager entityManager) {
        if (!entityManager.contains(item)) {
            entityManager.persist(item);
        }
    }

}
