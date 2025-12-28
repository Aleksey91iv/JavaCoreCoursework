package service.javasubject;

import model.Question;
import org.springframework.stereotype.Service;
import service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final HashSet<Question> questions;
    private Long lastId;

    public JavaQuestionService() {
        questions = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question tempQuestion = new Question(0l, question, answer);
        if (question == null || questions.contains(tempQuestion)) {
            return null;
        }
        questions.add(tempQuestion);
        return tempQuestion;
    }

    @Override
    public Question add(Question question) {
        if (question == null || questions.contains(question)) {
            return null;
        }
        questions.add(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        Random rnd = new Random();
        int i = rnd.nextInt(questions.size());
        return  questions.stream().toList().get(i);
    }

    @Override
    public Question remove(Question question) {
        return questions.remove(question) ? question : null;
    }
}
