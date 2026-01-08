package ru.coursework2.JavaCOR;

import ru.coursework2.JavaCOR.model.Question;
import ru.coursework2.JavaCOR.model.QuestionsRepository;
import ru.coursework2.JavaCOR.model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import ru.coursework2.JavaCOR.service.ExaminerServiceImpl;
import ru.coursework2.JavaCOR.service.JavaQuestionService;
import ru.coursework2.JavaCOR.service.QuestionStorageServices;

import java.util.Collection;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTests {
    @Mock
    private QuestionStorageServices questionServicesStorage;

    @Mock
    private QuestionsRepository questionsRepository;

    @Test
    void isNotFoundSubjectService() {
        ExaminerServiceImpl testedService = new ExaminerServiceImpl(questionServicesStorage);

        Mockito.doReturn(null).when(questionServicesStorage).getQuestionService(Subject.JAVA);
        HttpClientErrorException httpClientErrorException = null;
        if (Assertions.assertThrows(Exception.class,
            () -> testedService.getQuestions(Subject.JAVA, 5)) instanceof HttpClientErrorException resultException) {
            httpClientErrorException = resultException;
        }

        Assertions.assertTrue(httpClientErrorException != null &&
            httpClientErrorException.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    void amountIsMoreSubjectQuestions() {
        ExaminerServiceImpl testedService = new ExaminerServiceImpl(questionServicesStorage);

        Mockito.doReturn(new JavaQuestionService(questionsRepository)).when(questionServicesStorage).getQuestionService(Subject.JAVA);
        HashSet<Question> questions = new HashSet<>();
        questions.add(new Question("1+1", "2"));
        questions.add(new Question("1+2", "3"));
        Mockito.doReturn(questions).when(questionsRepository).getAll();

        HttpClientErrorException httpClientErrorException = null;
        if (Assertions.assertThrows(Exception.class,
                () -> testedService.getQuestions(Subject.JAVA, 5)) instanceof HttpClientErrorException resultException) {
            httpClientErrorException = resultException;
        }

        Assertions.assertTrue(httpClientErrorException != null &&
                httpClientErrorException.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    void amountQuestionsIsExist() {
        ExaminerServiceImpl testedService = new ExaminerServiceImpl(questionServicesStorage);

        Mockito.doReturn(new JavaQuestionService(questionsRepository)).when(questionServicesStorage).getQuestionService(Subject.JAVA);
        HashSet<Question> questions = new HashSet<>();
        questions.add(new Question("1+1", "2"));
        questions.add(new Question("1+2", "3"));
        questions.add(new Question("1+3", "4"));
        questions.add(new Question("1+4", "5"));
        questions.add(new Question("1+5", "6"));
        Mockito.doReturn(questions).when(questionsRepository).getAll();

        Integer amount = 3;
        Collection<Question> questionsResult= testedService.getQuestions(Subject.JAVA, amount);
        Assertions.assertTrue(questionsResult != null &&
            questionsResult.stream().count() == amount &&
            questionsResult.stream().count() <= questions.stream().count() &&
            questions.containsAll(questionsResult));
    }
}
