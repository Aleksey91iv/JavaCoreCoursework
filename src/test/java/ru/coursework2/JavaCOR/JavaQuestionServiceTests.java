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
import org.springframework.stereotype.Repository;
import ru.coursework2.JavaCOR.service.JavaQuestionService;
import ru.coursework2.JavaCOR.service.QuestionStorageServices;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTests {
    @Mock
    private QuestionsRepository questionsRepository;

    @Mock
    private QuestionStorageServices questionServicesStorage;

    @Test
    void isAddQuestionFromFields() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Question empty = new Question("yes?", "");
        Question emptyAnswer = new Question("yes?", "");
        Question emptyQuestion = new Question("", "no");
        Question notExistQuestion = new Question("yes?", "no");
        Question existQuestion = new Question(notExistQuestion.getQuestion(), notExistQuestion.getAnswer());

        Question emptyResult = testedService.add(empty.getQuestion(), empty.getAnswer());
        Question emptyAnswerResult = testedService.add(emptyAnswer.getQuestion(), emptyAnswer.getAnswer());
        Question emptyQuestionResult = testedService.add(emptyQuestion.getQuestion(), emptyQuestion.getAnswer());

        Mockito.doReturn(notExistQuestion).when(questionsRepository).add(notExistQuestion);
        Question notExistQuestionResult = testedService.add(notExistQuestion.getQuestion(), notExistQuestion.getAnswer());

        Mockito.doReturn(null).when(questionsRepository).add(existQuestion);
        Question existQuestionResult = testedService.add(existQuestion.getQuestion(), existQuestion.getAnswer());

        Assertions.assertTrue(emptyResult == null &&
            emptyAnswerResult == null &&
            emptyQuestionResult == null &&
            existQuestionResult == null &&
            notExistQuestionResult != null &&
            notExistQuestionResult.equals(notExistQuestion));
    }

    @Test
    void isAddQuestionObjectExist() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Question question = new Question("yes?", "no");

        Mockito.doReturn(null).when(questionsRepository).add(null);
        Question addNullResult = testedService.add(null);

        Mockito.doReturn(question).when(questionsRepository).add(question);
        Question addNotExistQuestion = testedService.add(question);

        Mockito.doReturn(null).when(questionsRepository).add(question);
        Question addExistQuestion = testedService.add(question);

        Assertions.assertTrue(addNullResult == null &&
            addExistQuestion == null &&
            addNotExistQuestion != null &&
            addNotExistQuestion.equals(new Question("yes?", "no")));
    }

    @Test
    void isGetAllFromNotEmpty() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Set<Question> questionsMock = new HashSet<>();
        questionsMock.add(new Question("1+1", "2"));
        questionsMock.add(new Question("1+2", "3"));
        questionsMock.add(new Question("1+3", "4"));

        Mockito.doReturn(questionsMock).when(questionsRepository).getAll();

        Collection<Question> result = testedService.getAll();
        Assertions.assertTrue(result != null &&
            result.stream().filter(
        item -> questionsMock
                .stream()
                .toList()
                .contains(item))
                    .count() == questionsMock
                    .stream()
                    .count());
    }

    @Test
    void isGetAllFromEmpty() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Set<Question> questionsMock = new HashSet<>();
        Mockito.doReturn(questionsMock).when(questionsRepository).getAll();
        Collection<Question> result = testedService.getAll();
        Assertions.assertTrue(result != null &&
            result.isEmpty() &&
            questionsMock.isEmpty());
    }

    @Test
    void isGetRandomQuestionFromEmpty() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Set<Question> questionsMock = new HashSet<>();
        Mockito.doReturn(questionsMock).when(questionsRepository).getAll();

        Question question = testedService.getRandomQuestion();
        Assertions.assertTrue(question == null);
    }

    @Test
    void isGetRandomQuestionFromNotEmpty() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Set<Question> questionsMock = new HashSet<>();
        questionsMock.add(new Question("1+1", "2"));
        questionsMock.add(new Question("1+2", "3"));
        questionsMock.add(new Question("1+3", "4"));

        Mockito.doReturn(questionsMock).when(questionsRepository).getAll();

        Question question = testedService.getRandomQuestion();
        Assertions.assertTrue(question != null &&
            !question.getQuestion().isBlank() &&
            !question.getAnswer().isBlank() &&
            questionsMock.contains(question));
    }

    @Test
    void isRemovableQuestionExist() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Question question = new Question("yes?", "no");
        Mockito.doReturn(question).when(questionsRepository).remove(question);
        Question questionResult = testedService.remove(question);
        Assertions.assertTrue(questionResult == question);
    }

    @Test
    void isRemovableQuestionNotExist() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);

        Question question = new Question("yes?", "no");
        Mockito.doReturn(null).when(questionsRepository).remove(question);
        question = testedService.remove(question);
        Assertions.assertTrue(question == null);
    }

    @Test
    void isGetSubjectJava() {
        JavaQuestionService testedService = new JavaQuestionService(questionsRepository, questionServicesStorage);
        Assertions.assertTrue(testedService.getSubject() == Subject.JAVA);
    }
}
