package com.jeewaeducation.application_tracking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
