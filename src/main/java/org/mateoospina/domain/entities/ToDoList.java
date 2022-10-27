package org.mateoospina.domain.entities;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "Note")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@EntityListeners( AuditingEntityListener.class )
public class ToDoList {

    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private Date createdDate = Calendar.getInstance().getTime();

    private Date completionDate;

    private Boolean completed;

    private String user;

    private List<Item> items;
}
