package ru.coursework2.JavaCOR.controller;

import org.springframework.web.bind.annotation.*;
import ru.coursework2.JavaCOR.model.Question;
import ru.coursework2.JavaCOR.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import ru.coursework2.JavaCOR.service.ExaminerService;
import ru.coursework2.JavaCOR.service.ExaminerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("exam")
public class ExamController {

    @Autowired
    private final ExaminerService examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Question>> getExamQuestions(@RequestParam String subject, @RequestParam Integer amount) {
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
