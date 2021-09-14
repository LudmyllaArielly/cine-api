package com.ludmylla.cineapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StoryWithWrongStatusException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public StoryWithWrongStatusException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
