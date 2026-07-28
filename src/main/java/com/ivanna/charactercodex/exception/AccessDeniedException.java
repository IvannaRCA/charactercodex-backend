package com.ivanna.charactercodex.exception;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException(Class<?> entity) {
        super("You do not have access to this " + entity.getSimpleName());
    }
}
