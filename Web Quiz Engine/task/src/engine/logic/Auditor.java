package engine.logic;

import engine.model.Answer;
import engine.model.Quiz;

import java.util.Collections;
import java.util.List;

public class Auditor {

    private Quiz quiz;

    public Auditor(Quiz quiz) {
        this.quiz = quiz;
    }

    public ResponseQuiz getResponseQuiz(Answer answer) {
        boolean success = false;
        List<Integer> rightAnswers = quiz.getAnswer();
        List<Integer> inAnswers  = answer.getAnswer();
        if (rightAnswers.size() != inAnswers.size()) {
            success = false;
        } else {
            success = rightAnswers.containsAll(inAnswers);
        }
        return new ResponseQuiz(success);
    }
}
