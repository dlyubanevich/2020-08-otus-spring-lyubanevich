package ru.otus.dlyubanevich.batch;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.dlyubanevich.batch.writer.SummaryItemWriter;
import ru.otus.dlyubanevich.domain.Summary;
import ru.otus.dlyubanevich.domain.Statistic;
import ru.otus.dlyubanevich.service.SummaryService;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@AllArgsConstructor
@Configuration
public class SummaryStepConfig {

    private static final int CHUNK_SIZE = 5;

    private final StepBuilderFactory stepBuilderFactory;
    private final SummaryService summaryService;

    @Bean
    public Step importToH2(MongoTemplate mongoTemplate, SummaryItemWriter writer){
        return stepBuilderFactory
                .get("importToH2")
                .<Statistic, Summary>chunk(CHUNK_SIZE)
                .reader(reader(mongoTemplate))
                .processor(processor())
                .writer(writer)
                .build();
    }

    public MongoItemReader<Statistic> reader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<Statistic>()
                .name("statisticItemReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(Statistic.class)
                .sorts(new HashMap<>())
                .build();
    }

    public ItemProcessor<Statistic, Summary> processor(){
        return summaryService::transform;
    }

}
