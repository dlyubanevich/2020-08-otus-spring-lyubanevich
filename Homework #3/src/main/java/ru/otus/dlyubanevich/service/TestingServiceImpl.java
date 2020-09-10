package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.config.YmlProperties;
import ru.otus.dlyubanevich.domain.Student;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestingServiceImpl implements TestingService {

    private final TaskService taskService;
    private final InputOutputService inputOutputService;
    private final StudentService studentService;
    private final MessageSource messageSource;
    private final YmlProperties ymlProperties;

    private Student student;
    private int score;

    @Override
    public void run() {
        registerStudent();
        printGreetingMessage();
        testingProcess();
        showResults();
    }

    private void registerStudent() {
        inputOutputService.println("");
        var message = getMessageLocale("student.nameRequest", new String[]{});
        inputOutputService.print(message);
        String name = inputOutputService.scan();
        student = studentService.findByName(name);
    }

    private void printGreetingMessage() {
        var message = getMessageLocale("message.greeting", new String[]{});
        inputOutputService.println(message);
    }

    private void testingProcess() {
        List<Task> tasks = taskService.getTasks();
        for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {

            Task task = tasks.get(taskIndex);

            printTask(task, taskIndex+1);
            updateScore(task);

        }
    }

    private void printTask(Task task, int questionNumber) {
        printQuestion(task.getQuestion(), questionNumber);
        printOptions(task.getOptions());
    }

    private void printQuestion(String question, int questionNumber) {
        inputOutputService.println("");
        var message = getMessageLocale("question.info", new String[]{String.valueOf(questionNumber)});
        inputOutputService.println(message);
        inputOutputService.println(question);
    }

    private void printOptions(List<String> options) {
        options.forEach(inputOutputService::println);
    }

    private void updateScore(Task task) {
        int studentAnswer = getStudentAnswer(task.getOptions().size());
        score += (studentAnswer == task.getAnswer()) ? 1 : 0;
    }

    private int getStudentAnswer(int max) {
        var info = getMessageLocale("options.info", new String[]{String.valueOf(max)});
        var error = getMessageLocale("error.info", new String[]{});
        int studentAnswer = 0;
        while (studentAnswer == 0){
            inputOutputService.print(info);
            try {
                studentAnswer = Integer.parseInt(inputOutputService.scan());
            }catch (NumberFormatException exception){
                inputOutputService.println(error);
            }
            if (studentAnswer > max){
                studentAnswer = 0;
            }
        }
        return studentAnswer;
    }

    private void showResults() {
        inputOutputService.println("");
        var result = getMessageLocale("student.result", new String[]{student.getName(), String.valueOf(score)});
        inputOutputService.println(result);
    }

    private String getMessageLocale(String param, String[] args) {
        return messageSource.getMessage(param, args, ymlProperties.getLocale());
    }
}
