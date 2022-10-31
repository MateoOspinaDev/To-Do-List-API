package org.mateoospina.infrastructure.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ToDoListNotFoundException extends RuntimeException {
    public ToDoListNotFoundException(String message) {
        super(message);
    }
}
