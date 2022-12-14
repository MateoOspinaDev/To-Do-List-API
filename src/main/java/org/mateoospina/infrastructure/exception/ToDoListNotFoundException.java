package org.mateoospina.infrastructure.exception;

import lombok.Data;

@Data
public class ToDoListNotFoundException extends RuntimeException {
    public ToDoListNotFoundException(String message) {
        super(message);
    }
}
