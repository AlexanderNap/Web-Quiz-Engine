package engine.model;

import java.util.List;

public class Answer {

    private List<Integer> answer;

    public Answer() {}

    public Answer(List<Integer> answers) {
        this.answer = answers;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

}