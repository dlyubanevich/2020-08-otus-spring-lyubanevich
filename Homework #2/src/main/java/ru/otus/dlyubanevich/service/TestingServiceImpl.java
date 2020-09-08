package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.domain.Student;
import ru.otus.dlyubanevich.domain.Task;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestingServiceImpl implements TestingService {

    private final TaskService taskService;
    private final InputOutputService inputOutputService;
    private final StudentService studentService;
    private final MessageService messageService;

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
        inputOutputService.print(messageService.getStudentNameRequest());
        String name = inputOutputService.scan();
        student = studentService.findByName(name);
    }

    private void printGreetingMessage() {
        inputOutputService.println(messageService.getGreetingMessage());
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
        inputOutputService.print(messageService.getIndent());
        inputOutputService.println(messageService.getQuestionInfo(questionNumber));
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
        String info = messageService.getOptionsInfo(max);
        int studentAnswer = 0;
        while (studentAnswer == 0){
            inputOutputService.print(info);
            try {
                studentAnswer = Integer.parseInt(inputOutputService.scan());
            }catch (NumberFormatException exception){
                inputOutputService.println(messageService.getErrorInfo());
            }
            if (studentAnswer > max){
                studentAnswer = 0;
            }
        }
        return studentAnswer;
    }

    private void showResults() {
        String result = messageService.getTestResult(student.getName(), score);
        inputOutputService.print(messageService.getIndent());
        inputOutputService.println(result);
    }
}
