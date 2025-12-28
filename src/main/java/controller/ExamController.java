package controller;

import model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import service.ExaminerService;
import service.ExaminerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("exam/java")
public class ExamController {

    @Autowired
    private final ExaminerService examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("{amount}")
    public ResponseEntity<Collection<Question>> getExamQuestions(@PathVariable Integer amount) {
        try {
            return ResponseEntity.ok(examinerService.getQuestions(amount));
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
