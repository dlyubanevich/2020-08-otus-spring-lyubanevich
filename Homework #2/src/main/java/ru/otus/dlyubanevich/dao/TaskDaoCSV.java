package ru.otus.dlyubanevich.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.dlyubanevich.domain.Task;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
public class TaskDaoCSV implements TaskDao{

    private final String resource;

    public TaskDaoCSV(@Value("${resource}") String resource) {
        this.resource = resource;
    }

    @Override
    public List<Task> getTasks() {
        InputStream stream = getStreamFromResource(resource);
        return buildTasksFromCSV(stream);
    }

    private InputStream getStreamFromResource(String resource) {
        ClassLoader loader = getClass().getClassLoader();
        return loader.getResourceAsStream(resource);
    }

    @SneakyThrows
    private List<Task> buildTasksFromCSV(InputStream stream) {

        InputStreamReader reader = new InputStreamReader(stream);

        CsvToBeanFilter filter = strings ->
                (strings.length == 3) && Arrays.stream(strings).noneMatch(s -> s.length()==0);

        List<Task> tasks = new CsvToBeanBuilder<Task>(reader)
                .withType(Task.class)
                .withFilter(filter)
                .build()
                .parse();

        reader.close();
        stream.close();

        return tasks;
    }

}
