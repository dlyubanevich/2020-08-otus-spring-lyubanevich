package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dlyubanevich.domain.Student;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Класс TestingServiceImpl")
class TestingServiceImplTest {

    @MockBean
    private TaskService taskService;
    @MockBean
    private StudentService studentService;
    @MockBean
    private IOMessageService messageService;

    @Autowired
    private TestingServiceImpl testingService;

    @Nested
    @DisplayName("Метод login")
    class Login{

        @Test
        @DisplayName("должен регистрировать студента по имени")
        void shouldHaveRegisterStudentByName() {
            String name = "Ivan";
            given(studentService.findByName(name)).willReturn(new Student(name));
            testingService.login(name);
            verify(studentService, times(1)).findByName(name);
        }

    }

    @Nested
    @DisplayName("Метод printGreetingMessage")
    class PrintGreetingMessage{

        @Test
        @DisplayName("должен выводить в консоль приветствие")
        void shouldPrintGreetingMessage() {
            testingService.printGreetingMessage();
            verify(messageService, times(1)).printMessage(anyString());
        }

    }

    @Nested
    @DisplayName("Метод testingProcess")
    class TestingProcess {

        @Test
        @DisplayName("должен получить задания для теста")
        void shouldGetTasks() {
            given(taskService.getTasks()).willReturn(new ArrayList<>());
            testingService.testingProcess();
            verify(taskService, times(1)).getTasks();
        }
    }

}