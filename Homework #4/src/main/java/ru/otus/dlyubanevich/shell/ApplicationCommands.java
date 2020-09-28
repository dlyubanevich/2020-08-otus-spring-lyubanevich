package ru.otus.dlyubanevich.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dlyubanevich.service.TestingService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final TestingService testingService;

    private String studentName;

    @ShellMethod(value = "Login student", key = {"l", "login"})
    public void login(@ShellOption String studentName){
        this.studentName = studentName;
        testingService.login(studentName);
    }

    @ShellMethod(value = "Run testing process", key = {"r", "run"})
    @ShellMethodAvailability(value = "isTestingProcessAvailable")
    public void run() {
        testingService.run();
    }

    private Availability isTestingProcessAvailable(){
        return studentName == null? Availability.unavailable("you are not logged in"): Availability.available();
    }
}
