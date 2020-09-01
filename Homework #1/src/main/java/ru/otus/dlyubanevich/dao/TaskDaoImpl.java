package ru.otus.dlyubanevich.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import ru.otus.dlyubanevich.domain.Task;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Data
public class TaskDaoImpl implements TaskDao{

    private final String resource;

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
        List<Task> tasks = new CsvToBeanBuilder<Task>(reader)
                .withType(Task.class)
                .build()
                .parse();
        reader.close();
        stream.close();
        return tasks;
    }

}
