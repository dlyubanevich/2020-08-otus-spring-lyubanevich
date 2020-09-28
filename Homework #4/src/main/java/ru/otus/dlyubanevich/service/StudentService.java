package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Student;

public interface StudentService {

    Student findByName(String name);

}
