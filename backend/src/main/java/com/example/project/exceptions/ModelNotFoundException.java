package com.example.project.exceptions;

// Custom exception for when an AI model is not found
public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}