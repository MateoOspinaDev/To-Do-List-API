package org.mateoospina.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String descripcion;
    private boolean done;
}
