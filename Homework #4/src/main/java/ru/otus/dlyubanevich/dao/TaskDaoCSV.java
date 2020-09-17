package ru.otus.dlyubanevich.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import org.springframework.stereotype.Repository;
import ru.otus.dlyubanevich.config.Properties;
import ru.otus.dlyubanevich.domain.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TaskDaoCSV implements TaskDao{

    private final Properties properties;

    public TaskDaoCSV(Properties properties) {
        this.properties = properties;
    }

    @Override
    public List<Task> getTasks() {
        InputStream stream = getStreamFromResource(properties.getTestingForm());
        List<Task> tasks = buildTasksFromCSV(stream);
        try {
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return tasks;
    }

    private InputStream getStreamFromResource(String resource) {
        ClassLoader loader = getClass().getClassLoader();
        return loader.getResourceAsStream(resource);
    }

    private List<Task> buildTasksFromCSV(InputStream stream) {

        CsvToBeanFilter filter = strings ->
                (strings.length == 3) && Arrays.stream(strings).noneMatch(s -> s.length()==0);

        List<Task> tasks = new ArrayList<>();

        try(InputStreamReader reader = new InputStreamReader(stream)) {
            tasks.addAll(new CsvToBeanBuilder<Task>(reader)
                    .withType(Task.class)
                    .withFilter(filter)
                    .build()
                    .parse()
            );
        }catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

}
