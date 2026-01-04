package service;

import model.QuestionsRepository;
import model.Subject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JavaQuestionService extends SubjectQuestionService {

    public JavaQuestionService(QuestionsRepository questionsRepository,
                               QuestionServicesStorage questionServicesStorage) {
        super(Subject.JAVA, questionsRepository, questionServicesStorage);
    }

    @Override
    public String getNameService() {
        return this.getClass().getName();
    }
}
