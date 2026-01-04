package controller;

import model.Question;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import service.ExaminerService;
import service.ExaminerServiceImpl;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("exam/test")
public class ExamController {

    @Autowired
    private final ExaminerService examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("{amount}")
    public ResponseEntity<Collection<Question>> getExamQuestions(@PathVariable String subject, @PathVariable Integer amount) {
        try {
            if (ObjectUtils.containsConstant(Subject.values(), subject.toUpperCase(), true)) {
                return ResponseEntity.ok(
                    examinerService.getQuestions(Subject.valueOf(subject.toUpperCase()), amount));
            }
            return ResponseEntity.badRequest().build();

        } catch (HttpClientErrorException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
