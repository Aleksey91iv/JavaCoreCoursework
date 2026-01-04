package service;

import model.Question;
import model.Subject;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ExaminerService {
    Collection<Question> getQuestions(Subject subject, Integer amount);
}
