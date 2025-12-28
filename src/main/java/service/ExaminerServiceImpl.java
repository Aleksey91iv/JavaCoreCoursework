package service;

import model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(Integer amount) {
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
}
