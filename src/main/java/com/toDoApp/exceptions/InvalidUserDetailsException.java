package com.toDoApp.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InvalidUserDetailsException extends RuntimeException {
    private String debugMessage;
    public InvalidUserDetailsException(String message, String debugMessage) {
        super(message);
        this.debugMessage=debugMessage;
    }
}