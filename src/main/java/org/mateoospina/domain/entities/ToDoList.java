package org.mateoospina.domain.entities;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    private String name;

    private String description;

    private Date createdDate = Calendar.getInstance().getTime();

    private Date completionDate;

    private Boolean completed;

    private String user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<Item> items;
}
