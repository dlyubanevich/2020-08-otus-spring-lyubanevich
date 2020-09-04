package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.dlyubanevich.config.TestingSpringConfig;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс TaskDaoImpl должен")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestingSpringConfig.class)
class TaskDaoImplTest {

    @Autowired
    private TaskDao taskDao;

    @DisplayName("Возвращать список заданий из файла, в которых заполнены все 3 поля")
    @Test
    void shouldHaveParseTheListOfTasks(){
        List<Task> tasks = taskDao.getTasks();
        assertThat(tasks).isNotNull().allMatch(task ->
                task.getQuestion() != null && task.getOptions() != null && task.getAnswer() != 0)
                .hasSize(1);
    }

}