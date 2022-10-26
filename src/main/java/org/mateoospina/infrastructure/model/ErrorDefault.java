package org.mateoospina.infrastructure.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDefault {
    private String code;
    private String message;

}
