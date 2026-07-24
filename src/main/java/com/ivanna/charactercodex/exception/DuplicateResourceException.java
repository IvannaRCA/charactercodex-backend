package com.ivanna.charactercodex.exception;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String resource) {
        super(resource + " already exists.");
    }
}
