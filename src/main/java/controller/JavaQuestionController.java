package controller;

import model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.QuestionService;
import service.javasubject.JavaQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/java")
public class JavaQuestionController {

    @Autowired
    private final QuestionService questionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.questionService = javaQuestionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@PathVariable String question, @PathVariable String answer) {
        Question returnedQuestion = questionService.add(question, answer);
        if (returnedQuestion == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedQuestion);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question returnedQuestion = questionService.add(question);
        if (returnedQuestion == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedQuestion);
    }

    @GetMapping("/allquestions")
    public Collection<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @GetMapping
    public ResponseEntity<Question> getRandomQuestion() {
        Question randomQuestion = questionService.getRandomQuestion();
        if (randomQuestion == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomQuestion);
    }

    @DeleteMapping
    public ResponseEntity<Question> deleteQuestion(@RequestBody Question question) {
        Question removedQuestion = questionService.remove(question);
        if (removedQuestion == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(removedQuestion);
    }
}
