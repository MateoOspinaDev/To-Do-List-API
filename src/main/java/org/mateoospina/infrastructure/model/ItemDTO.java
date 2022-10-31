package org.mateoospina.infrastructure.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ItemDTO {
    private Long id;
    @NotBlank(message = "Description is empty or null")
    private String description;
    private boolean done;
}
