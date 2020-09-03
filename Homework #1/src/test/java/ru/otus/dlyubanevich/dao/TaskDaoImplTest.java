package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс TaskDao")
class TaskDaoImplTest {

    private static final String RESOURCE = "test.csv";

    @DisplayName("Корректно создается конструктор")
    @Test
    void shouldHaveCorrectConstructor(){
        TaskDaoImpl taskDao = new TaskDaoImpl(RESOURCE);
        assertThat(taskDao.getResource())
                .isEqualTo(RESOURCE);
    }

    @DisplayName("Возвращает 5 успешно прочитанных заданий из файла")
    @Test
    void shouldHaveParseTheListOfTasks(){
        TaskDaoImpl taskDao = new TaskDaoImpl(RESOURCE);
        List<Task> tasks = taskDao.getTasks();
        assertThat(tasks)
                .filteredOn(task -> task.getQuestion() != null)
                .filteredOn(task -> task.getOptions() != null)
                .filteredOn(task -> task.getAnswer() > 0)
                .hasSize(5);
    }

}