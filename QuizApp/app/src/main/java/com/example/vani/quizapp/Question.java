package com.example.vani.quizapp;

public class Question {

    private String id, question, answer;

    public Question(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Question(String question) {
//        this.question=question;
//    }
//

    public Question(String question, String answer) {

        this.question=question;
        this.answer=answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
