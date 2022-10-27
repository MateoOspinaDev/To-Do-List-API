package org.mateoospina.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.listServices.ListMediator;
import org.mateoospina.domain.listServices.ListMediatorDefault;
import org.mateoospina.domain.persistence.ListRepository;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ListMediatorDefaultTest {
    @Mock
    private ListRepository repository;
    private ListMediator mediator;
    private ToDoList toDoListIn;

    @BeforeEach
    public void setup(){
        toDoListIn = ToDoList.builder()
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();

        ToDoList toDoListOut = ToDoList.builder()
                .id(100L)
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();

        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
        mediator = new ListMediatorDefault(repository);
    }

    @Test
    void shouldCreateAListSuccessful() {
        ToDoList listCreated = mediator.createList(toDoListIn);

        assertEquals(100, listCreated.getId());
        verify(repository).save(any(ToDoList.class));
    }

//    @Test
//    void shouldThrowAnIllegalArgumentExceptionWhenNameIsNull() {
//        toDoListIn.setName(null);
//
//        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
//                () -> mediator.create(toDoListIn),
//                "Se esperaba un IllegalArgumentException cuando Name es nulo, pero no fue lanzada");
//        assertTrue(exception.getMessage().contains("Name is empty"));
//        verify(repository, times(0)).save(any(ToDoList.class));
//    }
//
//    @Test
//    void shouldThrowAnIllegalArgumentExceptionWhenNameIsEmpty() {
//        toDoListIn.setName("");
//
//        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
//                () -> mediator.create(toDoListIn),
//                "Se esperaba un IllegalArgumentException cuando Name es vacio, pero no fue lanzada");
//        assertTrue(exception.getMessage().contains("Name is empty"));
//        verify(repository, times(0)).save(any(ToDoList.class));
//    }
//
//    @Test
//    void shouldThrowAnIllegalArgumentExceptionWhenUserIsNull() {
//        toDoListIn.setUser(null);
//
//        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
//                () -> mediator.create(toDoListIn),
//                "Se esperaba un IllegalArgumentException cuando User es nulo, pero no fue lanzada");
//        assertTrue(exception.getMessage().contains("User is empty"));
//        verify(repository, times(0)).save(any(ToDoList.class));
//    }
//
//    @Test
//    void shouldThrowAnIllegalArgumentExceptionWhenUserIsEmpty() {
//        toDoListIn.setUser("");
//
//        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
//                () -> mediator.create(toDoListIn),
//                "Se esperaba un IllegalArgumentException cuando User es vacio, pero no fue lanzada");
//        assertTrue(exception.getMessage().contains("User is empty"));
//        verify(repository, times(0)).save(any(ToDoList.class));
//    }
//
//    @Test
//    void shouldThrowAnIllegalArgumentExceptionWhenUserFormtIsNotCorrect() {
//        toDoListIn.setUser("cdanielmg200@");
//
//        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
//                () -> mediator.create(toDoListIn),
//                "Se esperaba un IllegalArgumentException cuando User tiene un formado errado, pero no fue lanzada");
//        assertTrue(exception.getMessage().contains("The user does not have the email format"));
//        verify(repository, times(0)).save(any(ToDoList.class));
//    }
}
