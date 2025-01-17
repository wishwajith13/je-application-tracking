package com.jeewaeducation.application_tracking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
