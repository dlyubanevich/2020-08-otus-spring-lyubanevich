package ru.otus.dlyubanevich.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.domain.Student;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

@Data
@Service
public class TestingServiceImpl implements TestingService {

    private final TaskService taskService;
    private final InputOutputService inputOutputService;
    private final StudentService studentService;

    private Student student;
    private int score;

    @Override
    public void run() {
        registerStudent();
        showGreetingMessage();
        testingProcess();
        showResults();
    }

    private void registerStudent() {
        inputOutputService.print("Please, enter your name:");
        String name = inputOutputService.scan();
        student = studentService.findByName(name);
    }

    private void showGreetingMessage() {
        String greeting = "\nHello,my friend! \nI want to know how awesome you are. Answer a few questions:\n";
        inputOutputService.println(greeting);
    }

    private void testingProcess() {
        List<Task> tasks = taskService.getTasks();
        for (int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);

            inputOutputService.println("Question №" + (i+1));
            inputOutputService.println(task.getQuestion());

            List<String> options = task.getOptions();
            options.forEach(
                    option -> inputOutputService.println("\t" + option)
            );

            int studentAnswer = getStudentAnswer(options.size());
            score += (studentAnswer == task.getAnswer()) ? 1 : 0;
            inputOutputService.print("\n");

        }
    }

    private int getStudentAnswer(int max) {
        String info = "Please choose option from 1 to " + max + ":";
        int studentAnswer = 0;
        while (studentAnswer == 0){
            inputOutputService.print(info);
            try {
                studentAnswer = Integer.parseInt(inputOutputService.scan());
            }catch (NumberFormatException exception){
                inputOutputService.println("\nWrong number format!\n");
            }
            if (studentAnswer > max){
                studentAnswer = 0;
            }
        }
        return studentAnswer;
    }

    private void showResults() {
        String result = "Dear " + student.getName() + ".\nYour score is " + score + ". Damn you're good!";
        inputOutputService.println(result);
    }

}
