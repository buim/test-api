package com.ag.test.exception;

import org.springframework.hateoas.ResourceSupport;

public class ErrorModel extends ResourceSupport {

    private static final String DEFAULT_ERROR_MESSAGE = "Unknown error";

    private int status; // HTTP Status

    private int code; // error code

    private String message;

    private Object data;

    public ErrorModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
