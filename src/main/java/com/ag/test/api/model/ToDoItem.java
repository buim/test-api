package com.ag.test.api.model;

import java.util.Date;

public class ToDoItem {
    private Long id;
    private String text;
    private Boolean isCompleted;
    private Date createdAt;

    public ToDoItem(Long id, String text, Boolean isCompleted, Date createdAt) {
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
