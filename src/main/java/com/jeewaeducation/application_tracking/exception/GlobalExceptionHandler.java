package com.jeewaeducation.application_tracking.exception;

import com.jeewaeducation.application_tracking.utility.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<StandardResponse>(new StandardResponse(404, "Not Found", exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<StandardResponse> handleDuplicateKeyException(DuplicateKeyException exception) {
        return new ResponseEntity<StandardResponse>(new StandardResponse(409, "Conflict", exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardResponse> handleIOException(IOException exception) {
        return new ResponseEntity<StandardResponse>(new StandardResponse(500, "Internal Server Error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<StandardResponse> handleFileNotFoundException(FileNotFoundException exception) {
        return new ResponseEntity<StandardResponse>(new StandardResponse(404, "File Not Found", exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
