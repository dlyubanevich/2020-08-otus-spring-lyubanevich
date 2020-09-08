package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.dao.StudentDao;
import ru.otus.dlyubanevich.domain.Student;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Override
    public Student findByName(String name) {
        return studentDao.findByName(name);
    }
}
