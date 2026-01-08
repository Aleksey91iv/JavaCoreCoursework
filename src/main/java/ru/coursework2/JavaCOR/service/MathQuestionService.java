package ru.coursework2.JavaCOR.service;

import ru.coursework2.JavaCOR.model.QuestionsRepository;
import ru.coursework2.JavaCOR.model.Subject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MathQuestionService extends SubjectQuestionService {
    public MathQuestionService(QuestionsRepository questionsRepository) {
        super(Subject.MATH, questionsRepository);
    }

    @Override
    public String getNameService() {
        return this.getClass().getName();
    }
}
