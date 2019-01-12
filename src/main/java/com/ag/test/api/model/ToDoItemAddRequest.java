package com.ag.test.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ToDoItemAddRequest {

    @NotNull
    @Size (min = 1, max = 50)
    private String text;

    public ToDoItemAddRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
