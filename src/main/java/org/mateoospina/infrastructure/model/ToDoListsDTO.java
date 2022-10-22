package org.mateoospina.infrastructure.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ToDoListsDTO {
    private long id;
    private String name;
    private String description;
    private String user;
    private Date date;
}
