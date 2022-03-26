package com.akvelon.task.tracker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception
 */
@Getter
public class TaskTrackerException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public TaskTrackerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
