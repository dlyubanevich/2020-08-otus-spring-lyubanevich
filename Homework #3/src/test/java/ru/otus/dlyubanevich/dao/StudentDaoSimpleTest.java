package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.dlyubanevich.domain.Student;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс StudentDaoImpl должен")
class StudentDaoSimpleTest {

    private final StudentDao studentDao = new StudentDaoSimple();

    @Test
    @DisplayName("возвращать студента по заданному имени")
    void shouldHaveStudentMock() {
        String name = "Ivan";
        assertThat(studentDao.findByName(name))
                .isNotNull()
                .hasFieldOrProperty("name")
                .extracting(Student::getName).isIn(name);

    }

}