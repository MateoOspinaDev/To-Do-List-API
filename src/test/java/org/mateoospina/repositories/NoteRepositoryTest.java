package org.mateoospina.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mateoospina.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void noteRepositoryTest(){
        Note note = new Note(1L,"Ir a comer","Ir a comer algo", LocalDate.now(),
                LocalDate.of(2021, 10, 10));
        Note noteGuardado  = noteRepository.save(note);
        Assertions.assertNotNull(noteGuardado);
    }
}
