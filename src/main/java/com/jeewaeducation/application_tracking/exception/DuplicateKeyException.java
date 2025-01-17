package com.jeewaeducation.application_tracking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException(String message) {
        super(message);
    }
}
