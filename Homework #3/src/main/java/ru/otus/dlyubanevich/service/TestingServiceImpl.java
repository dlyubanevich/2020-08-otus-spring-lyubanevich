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
    private final PrintMessageService printMessageService;

    private Student student;
    private int score;

    @Override
    public void run() {
        registerStudent();
        printGreetingMessage();
        testingProcess();
        showResults();
    }

    void registerStudent() {
        printMessageService.printMessage("student.nameRequest");
        setCurrentStudent(inputOutputService.scan());
    }

    private void setCurrentStudent(String name) {
        student = studentService.findByName(name);
    }

    void printGreetingMessage() {
        printMessageService.printMessage("message.greeting");
    }

    void testingProcess() {
        List<Task> tasks = taskService.getTasks();
        for (int taskIndex = 0; taskIndex < tasks.size(); taskIndex++) {
            Task task = tasks.get(taskIndex);
            printQuestionAndOptions(task, taskIndex+1);
            updateScore(task);
        }
    }

    void printQuestionAndOptions(Task task, int questionNumber) {
        printQuestionNumber(questionNumber);
        printQuestion(task.getQuestion());
        printOptions(task.getOptions());
    }

    private void printQuestionNumber(int questionNumber) {
        printMessageService.printMessage("question.info", String.valueOf(questionNumber));
    }

    void printQuestion(String question) {
        inputOutputService.println(question);
    }

    void printOptions(List<String> options) {
        options.forEach(inputOutputService::println);
    }

    void updateScore(Task task) {
        int studentAnswer = getStudentAnswer(task.getOptions().size());
        score += (studentAnswer == task.getAnswer()) ? 1 : 0;
    }

    int getStudentAnswer(int max) {
        int studentAnswer = 0;
        while (studentAnswer == 0){
            printMessageService.printMessage("options.info", String.valueOf(max));
            try {
                studentAnswer = Integer.parseInt(inputOutputService.scan());
            }catch (NumberFormatException exception){
                printMessageService.printMessage("error.info");
            }
            if (studentAnswer > max){
                studentAnswer = 0;
            }
        }
        return studentAnswer;
    }

    void showResults() {
        printMessageService.printMessage("student.result", student.getName(), String.valueOf(score));
    }

}
