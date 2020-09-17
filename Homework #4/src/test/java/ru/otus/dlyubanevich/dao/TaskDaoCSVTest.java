package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.dlyubanevich.config.Properties;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Класс TaskDaoCSV должен")
class TaskDaoCSVTest {

    @Mock
    private Properties properties;

    public TaskDaoCSVTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Возвращать список заданий из файла, в которых заполнены все 3 поля")
    void shouldHaveParseTheListOfTasks() {

        when(properties.getTestingForm()).thenReturn("test.csv");

        TaskDao taskDao = new TaskDaoCSV(properties);
        List<Task> tasks = taskDao.getTasks();
        assertThat(tasks).isNotNull().allMatch(task ->
                task.getQuestion() != null && task.getOptions() != null && task.getAnswer() != 0)
                .hasSize(1);

    }

}