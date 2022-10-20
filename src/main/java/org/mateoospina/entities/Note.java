package org.mateoospina.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Note")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@EntityListeners( AuditingEntityListener.class )
public class Note {

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

    @Column(unique = true)
    private String title;

    private String description;

    @CreatedDate
    private LocalDate createdDate =
            LocalDate.now();

    private LocalDate completionDate;
}
