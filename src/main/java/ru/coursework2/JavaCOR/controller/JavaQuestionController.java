package ru.coursework2.JavaCOR.controller;

import ru.coursework2.JavaCOR.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.coursework2.JavaCOR.service.JavaQuestionService;
import ru.coursework2.JavaCOR.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("java")
public class JavaQuestionController {

    @Autowired
    private final JavaQuestionService questionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.questionService = javaQuestionService;
    }

    @PostMapping("/addraw")
    @ResponseBody
    public ResponseEntity<Question> createQuestion(@RequestParam String question, @RequestParam String answer) {
        Question returnedQuestion = questionService.add(question, answer);
        if (returnedQuestion == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedQuestion);
    }

    @PostMapping("/addbody")
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

    @GetMapping("/random")
    public ResponseEntity<Question> getRandomQuestion() {
        Question randomQuestion = questionService.getRandomQuestion();
        if (randomQuestion == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomQuestion);
    }

    @DeleteMapping
    public ResponseEntity<Question> deleteQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.remove(question));
    }
}
