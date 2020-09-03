package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getTasks();

}
