package ru.otus.dlyubanevich.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dlyubanevich.service.SummaryService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final Job importFootballStats;
    private final JobLauncher jobLauncher;
    private final SummaryService summaryService;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "run")
    public void startMigrationJobWithJobLauncher() throws Exception {
        var param = new JobParametersBuilder().toJobParameters();
        JobExecution execution = jobLauncher.run(importFootballStats, param);
        System.out.println(execution);
    }
    
    @ShellMethod(value = "findAllSummary", key = "show")
    public void showResults(){
        var list = summaryService.getAllSummary();
        list.forEach(System.out::println);
    }
}
