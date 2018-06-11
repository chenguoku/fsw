package com.fsw.pojo;

public class TbTestQuestionWithBLOBs extends TbTestQuestion {
    private String question;

    private String options;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }
}