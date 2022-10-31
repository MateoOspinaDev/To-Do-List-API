package org.mateoospina.infrastructure.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ToDoListsDTO {
    private long id;
    @NotBlank(message = "Name is empty or null")
    private String name;
    private String description;
    @NotBlank(message = "User is empty or null")
    @Email(message = "User(email) format is not valid")
    private String user;
    private Date date= Calendar.getInstance().getTime();
    private Boolean done;
}
