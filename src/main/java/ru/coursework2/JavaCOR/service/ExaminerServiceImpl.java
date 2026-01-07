package ru.coursework2.JavaCOR.service;

import ru.coursework2.JavaCOR.model.Question;
import ru.coursework2.JavaCOR.model.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ExaminerServiceImpl extends NamedSingletonService implements ExaminerService {

    private final QuestionStorageServices questionServicesStorage;

    public ExaminerServiceImpl(QuestionStorageServices questionServicesStorage) {
        this.questionServicesStorage = questionServicesStorage;
    }

    @Override
    public Collection<Question> getQuestions(Subject subject, Integer amount) {
        QuestionService questionService = questionServicesStorage.getQuestionService(subject);
        if (questionService == null) {
            throw new HttpClientErrorException(HttpStatus.METHOD_NOT_ALLOWED);
        }

        Collection<Question> allQuestions = questionService.getAll();
        HashSet<Question> resultCollection = new HashSet<>();
        if (allQuestions.size() < amount || amount == 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        if (allQuestions.size() == amount) {
            return allQuestions;
        }

        for (int i = 0; i < amount; i++) {
            Question question = null;
            while (resultCollection.contains(question) || question == null) {
                question = questionService.getRandomQuestion();
            }
            resultCollection.add(question);
        }
        return resultCollection;
    }

    @Override
    public String getNameService() {
        return "ExamService";
    }
}
