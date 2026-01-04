package service;

import model.Question;
import model.QuestionsRepository;
import model.Subject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class SubjectQuestionService extends NamedSingletonService implements QuestionService {
    private final QuestionsRepository questionsRepository;
    private final Subject subject;

    public SubjectQuestionService(Subject subject,
                                  QuestionsRepository questionsRepository,
                                  QuestionServicesStorage questionServicesStorage) {
        this.questionsRepository = questionsRepository;
        this.subject = subject;
        if (questionServicesStorage != null) {
            questionServicesStorage.registerSubjectQuestionService(this);
        }
    }

    @Override
    public Question add(String question, String answer) {
        Question tempQuestion = new Question(question, answer);
        return questionsRepository.add(tempQuestion);
    }

    @Override
    public Question add(Question question) {
        return questionsRepository.add(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionsRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionsRepository.getAll().isEmpty()) {
            return null;
        }
        Random rnd = new Random();
        int i = rnd.nextInt(questionsRepository.getAll().size());
        return  questionsRepository.getAll().stream().toList().get(i);
    }

    @Override
    public Question remove(Question question) {
        return questionsRepository.remove(question);
    }

    @Override
    public Subject getSubject() {
        return subject;
    }
}
