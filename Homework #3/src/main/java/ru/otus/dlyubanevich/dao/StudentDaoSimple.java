package ru.otus.dlyubanevich.dao;

import org.springframework.stereotype.Repository;
import ru.otus.dlyubanevich.domain.Student;

@Repository
public class StudentDaoSimple implements StudentDao {

    @Override
    public Student findByName(String name) {
        return new Student(name);
    }
}
