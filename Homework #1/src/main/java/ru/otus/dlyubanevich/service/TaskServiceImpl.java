package ru.otus.dlyubanevich.service;

import lombok.Data;
import ru.otus.dlyubanevich.dao.TaskDao;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

@Data
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    @Override
    public List<Task> getTasks() {
        return taskDao.getTasks();
    }

}
