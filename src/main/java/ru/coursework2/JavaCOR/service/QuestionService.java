package ru.coursework2.JavaCOR.service;

import org.springframework.stereotype.Service;
import ru.coursework2.JavaCOR.model.Question;
import ru.coursework2.JavaCOR.model.Subject;

import java.util.Collection;

@Service
public interface QuestionService {
    Subject getSubject();
    Question add(String question, String answer);
    Question add(Question question);
    Question getRandomQuestion();
    Question remove(Question question);

    Collection<Question> getAll();
}
