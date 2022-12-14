package org.mateoospina.infrastructure.exception;

import lombok.Data;

@Data
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
