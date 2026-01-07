package ru.coursework2.JavaCOR.service;

import org.springframework.stereotype.Service;
import ru.coursework2.JavaCOR.model.Question;
import ru.coursework2.JavaCOR.model.Subject;

import java.util.Collection;

@Service
public interface ExaminerService {
    Collection<Question> getQuestions(Subject subject, Integer amount);
}
