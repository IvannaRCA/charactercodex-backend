package com.ivanna.charactercodex.exception;

import java.util.List;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(List<String> entities) {
        super("Only " + entities + "can do this");
    }
}
