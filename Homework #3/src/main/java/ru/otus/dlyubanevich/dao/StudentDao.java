package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Student;

public interface StudentDao {
    Student findByName(String name);
}
