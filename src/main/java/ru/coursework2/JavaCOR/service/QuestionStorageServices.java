package ru.coursework2.JavaCOR.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.coursework2.JavaCOR.model.Subject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class QuestionStorageServices {
    private final HashMap<Subject, QuestionService> questionServices;

    public QuestionStorageServices(@Autowired List<QuestionService> questionServices) {
        this.questionServices = new HashMap<>();
        questionServices.forEach(qs -> this.questionServices.put(qs.getSubject(), qs));
    }

    public Map<Subject, QuestionService> getQuestionServices() {
        return Collections.unmodifiableMap(questionServices);
    }

    public QuestionService getQuestionService(Subject subject) {
        return questionServices.getOrDefault(subject, null);
    }
}
