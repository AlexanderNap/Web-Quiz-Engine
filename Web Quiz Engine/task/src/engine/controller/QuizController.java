package engine.controller;

import engine.logic.Auditor;
import engine.model.Answer;
import engine.model.Quiz;
import engine.logic.ResponseQuiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;


    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz createNewQuiz(@RequestBody @Valid Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizRepository.findById(id).get();
    }

    @GetMapping("/api/quizzes")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) quizRepository.findAll();
    }

    @PostMapping(value = "/api/quizzes/{id}/solve")
    public ResponseQuiz solveQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).get();
        return new Auditor(quiz).getResponseQuiz(answer);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    HashMap<String, String> handleMethodArgumentNotValidException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

    @ExceptionHandler({IndexOutOfBoundsException.class, NoSuchElementException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}