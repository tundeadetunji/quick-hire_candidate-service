package io.github.tundeadetunji.candidate_service.exception;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}