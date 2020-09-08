package ru.otus.dlyubanevich.dao;

import org.springframework.stereotype.Component;
import ru.otus.dlyubanevich.domain.Student;

@Component
public class StudentDaoSimple implements StudentDao {

    @Override
    public Student findByName(String name) {
        return new Student(name);
    }
}
