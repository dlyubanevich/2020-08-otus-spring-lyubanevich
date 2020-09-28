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
    private final StudentService studentService;
    private final IOMessageService IOMessageService;

    private Student student;
    private int score;

    @Override
    public void run() {
        printGreetingMessage();
        testingProcess();
        showResults();
    }

    @Override
    public void login(String studentName) {
        student = studentService.findByName(studentName);
    }

    void printGreetingMessage() {
        IOMessageService.printMessage("message.greeting");
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
        IOMessageService.printMessage("question.info", String.valueOf(questionNumber));
    }

    void printQuestion(String question) {
        IOMessageService.print(question);
    }

    void printOptions(List<String> options) {
        options.forEach(IOMessageService::print);
    }

    void updateScore(Task task) {
        int studentAnswer = getStudentAnswer(task.getOptions().size());
        score += (studentAnswer == task.getAnswer()) ? 1 : 0;
    }

    int getStudentAnswer(int max) {
        int studentAnswer = 0;
        while (studentAnswer == 0){
            IOMessageService.printMessage("options.info", String.valueOf(max));
            try {
                studentAnswer = Integer.parseInt(IOMessageService.readAnswer());
            }catch (NumberFormatException exception){
                IOMessageService.printMessage("error.info");
            }
            if (studentAnswer > max){
                studentAnswer = 0;
            }
        }
        return studentAnswer;
    }

    void showResults() {
        IOMessageService.printMessage("student.result", student.getName(), String.valueOf(score));
    }

}
