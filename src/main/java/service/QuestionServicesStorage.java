package service;

import model.Subject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class QuestionServicesStorage {
    private final HashMap<Subject, QuestionService> questionServices;

    public QuestionServicesStorage() {
        questionServices = new HashMap<>();
    }

    public void registerSubjectQuestionService(SubjectQuestionService questionService) {
        if (questionService == null ||
            questionServices.containsKey(questionService.getSubject()) ||
            questionServices.containsValue(questionService)) {
            return;
        }
        questionServices.put(questionService.getSubject(), questionService);
    }

    public QuestionService getQuestionService(Subject subject) {
        return questionServices.getOrDefault(subject, null);
    }
}
