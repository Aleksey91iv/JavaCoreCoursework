package model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionsRepository {
    private final HashSet<Question> questions;

    public QuestionsRepository() {
        questions = new HashSet<>();
    }

    public Question add(Question question) {
        if (question == null || questions.contains(question)) {
            return null;
        }
        questions.add(question);
        return question;
    }

    public Collection<Question> getAll() {
        return questions;
    }

    public Question remove(Question question) {
        return questions.remove(question) ? question : null;
    }
}
