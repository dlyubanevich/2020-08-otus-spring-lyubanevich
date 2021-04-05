package ru.otus.dlyubanevich.batch;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    public static final String IMPORT_SUMMARY_JOB_NAME = "importFootballStats";

    @Bean
    public Job importFootballStats(Step migrateSummaryStep){
        return jobBuilderFactory.get(IMPORT_SUMMARY_JOB_NAME)
                .flow(migrateSummaryStep)
                .end()
                .build();
    }

}
