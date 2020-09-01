package ru.otus.dlyubanevich.service;

import lombok.Data;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

@Data
public class TestingServiceImpl implements TestingService {

    private final TaskService taskService;
    private final InputOutputService inputOutputService;

    @Override
    public void run() {
        List<Task> tasks = taskService.getTasks();
        for (Task task : tasks) {
            inputOutputService.print(task.getQuestion());
            task.getOptions().forEach(
                    option -> inputOutputService.print("\t" + option)
            );
        }
    }

}
