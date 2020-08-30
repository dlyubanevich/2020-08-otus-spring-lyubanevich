package ru.otus.dlyubanevich;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.dlyubanevich.domain.Task;
import ru.otus.dlyubanevich.service.TestService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        TestService testService = context.getBean(TestService.class);
        List<Task> tasks = testService.getTasks();

        showTasks(tasks);

        context.close();

    }

    private static void showTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task.getQuestion());
            task.getOptions().forEach(option -> System.out.println("\t" + option));
        }
    }

}
