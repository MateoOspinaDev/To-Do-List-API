package org.mateoospina.domain.entities;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "to_do_List")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@EntityListeners( AuditingEntityListener.class )
public class ToDoList {

    @SequenceGenerator(
            name = "toDoList_sequence",
            sequenceName = "toDoList_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "toDoList_sequence"
    )
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is empty or null")
    private String name;

    private String description;

    @NotBlank(message = "User is empty or null")
    @Email(message = "User(email) format is not valid")
    private String user;

    private Date date;

    private Boolean done;

    //private List<Item> items;
}
