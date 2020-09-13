package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.dlyubanevich.domain.Student;

import static org.mockito.Mockito.*;

@DisplayName("Класс TestingServiceImpl")
class TestingServiceImplTest {

    @Mock
    private TaskService taskService;
    @Mock
    private InputOutputService inputOutputService;
    @Mock
    private StudentService studentService;
    @Mock
    private PrintMessageService printMessageService;

    private TestingServiceImpl testingService;

    @BeforeEach
    public void beforeEach(){
        testingService =
                new TestingServiceImpl(taskService, inputOutputService, studentService, printMessageService);
    }
    public TestingServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Метод registerStudent")
    class RegisterStudent{

        @Test
        @DisplayName("должен запрашивать имя студента")
        void registerStudent_ShouldHaveReadName() {
            when(inputOutputService.scan()).thenReturn(anyString());
            testingService.registerStudent();
            verify(inputOutputService, times(1)).scan();
        }

        @Test
        @DisplayName("должен создавать студента с указанным именем")
        void registerStudent_ShouldHaveCreateStudentWithSpecifiedName() {
            String name = "Ivan";
            when(inputOutputService.scan()).thenReturn(name);
            when(studentService.findByName(name)).thenReturn(new Student(name));
            testingService.registerStudent();
            verify(studentService, times(1)).findByName(name);
        }

    }

    @Nested
    @DisplayName("Метод printGreetingMessage")
    class PrintGreetingMessage{

    }
    @Nested
    @DisplayName("Метод testingProcess")
    class TestingProcess{

    }
    @Nested
    @DisplayName("Метод showResults")
    class ShowResults{

    }

}