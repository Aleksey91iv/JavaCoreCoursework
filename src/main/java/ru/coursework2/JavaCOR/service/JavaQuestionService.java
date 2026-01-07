package ru.coursework2.JavaCOR.service;

import ru.coursework2.JavaCOR.model.QuestionsRepository;
import ru.coursework2.JavaCOR.model.Subject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JavaQuestionService extends SubjectQuestionService {

    public JavaQuestionService(QuestionsRepository questionsRepository,
                               QuestionStorageServices questionServicesStorage) {
        super(Subject.JAVA, questionsRepository, questionServicesStorage);
    }

    @Override
    public String getNameService() {
        return this.getClass().getName();
    }
}
