package com.comma.user.service;

public class ValidationException extends Exception {
    private String inputId;

    public ValidationException(String message, String inputId) {
        super(message);
        this.inputId = inputId;
    }

    public String getInputId() {
        return inputId;
    }
}