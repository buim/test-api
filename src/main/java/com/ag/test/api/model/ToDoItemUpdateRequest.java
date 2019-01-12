package com.ag.test.api.model;

public class ToDoItemUpdateRequest {

    private String text;

    private Boolean isCompleted;

    public ToDoItemUpdateRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
