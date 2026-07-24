package com.ivanna.charactercodex.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class<?> entity){
        super("Entity " + entity.getSimpleName() + " not found.");
    }

    public EntityNotFoundException(Class<?> entity, String identifier) {
        super("Entity " + entity.getSimpleName() + " not found with identifier: " + identifier);
    }
}
