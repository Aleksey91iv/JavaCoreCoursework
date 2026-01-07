package ru.coursework2.JavaCOR.service;

import ru.coursework2.JavaCOR.model.Question;
import ru.coursework2.JavaCOR.model.QuestionsRepository;
import ru.coursework2.JavaCOR.model.Subject;

import java.util.*;

public abstract class SubjectQuestionService extends NamedSingletonService implements QuestionService {
    private final QuestionsRepository questionsRepository;
    private final Subject subject;

    public SubjectQuestionService(Subject subject,
                                  QuestionsRepository questionsRepository,
                                  QuestionStorageServices questionServicesStorage) {
        this.questionsRepository = questionsRepository;
        this.subject = subject;
        if (questionServicesStorage != null) {
            questionServicesStorage.registerSubjectQuestionService(this);
        }
    }

    @Override
    public Question add(String question, String answer) {
        if (question.isBlank() || answer.isBlank()) {
            return null;
        }
        Question tempQuestion = new Question(question, answer);
        return questionsRepository.add(tempQuestion);
    }

    @Override
    public Question add(Question question) {
        return questionsRepository.add(question);
    }

    @Override
    public Collection<Question> getAll() {
        Collection<Question> allQuestions = questionsRepository.getAll();
        return allQuestions != null ? allQuestions : new HashSet<>();
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
