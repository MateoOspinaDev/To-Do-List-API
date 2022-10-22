package org.mateoospina.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ToDoListRepositoryTest {

//    @Autowired
//    private NoteRepository noteRepository;
//
//    @Test
//    void noteRepositoryTest(){
//        ToDoList toDoList = new ToDoList(1L,"Ir a comer","Ir a comer algo", LocalDate.now(),
//                LocalDate.of(2021, 10, 10),true);
//        ToDoList toDoListGuardado = noteRepository.save(toDoList);
//        Assertions.assertNotNull(toDoListGuardado);
//    }
}
