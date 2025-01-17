package com.jeewaeducation.application_tracking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class IOException extends RuntimeException {
    public IOException(String message) {
        super(message);
    }
}
