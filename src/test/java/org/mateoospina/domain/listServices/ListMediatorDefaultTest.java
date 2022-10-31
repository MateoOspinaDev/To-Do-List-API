package org.mateoospina.domain.listServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.mockito.Mock;

import static java.util.Optional.ofNullable;
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
                .name("Mateo Ospina")
                .description("Comprar taladro para reparar la mesa")
                .user("mateo@gmail.com")
                .build();

        ToDoList toDoListOut = ToDoList.builder()
                .id(100L)
                .name("Comprar taladro")
                .description("Comprar taladro para reparar la mesa")
                .user("mateo@gmail.com")
                .build();

        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
        when(repository.findById(any(Long.class))).thenReturn(ofNullable(toDoListOut));
        mediator = new ListMediatorDefault(repository);
    }

    @Test
    void shouldCreateAListSuccessful() {
        ToDoList listCreated = mediator.createList(toDoListIn);

        assertEquals(100, listCreated.getId());
        verify(repository).save(any(ToDoList.class));
    }

    @Test
    void shouldGetAListSuccessful() {
        when(repository.existsById(any(Long.class))).thenReturn(true);
        ToDoList listFound = mediator.getListById(100L);

        assertEquals(100, listFound.getId());
        verify(repository).findById(100L);
        verify(repository).existsById(100L);
    }

    @Test
    void shouldDeleteAListSuccessful() {
        when(repository.existsById(any(Long.class))).thenReturn(true);
        mediator.deleteListById(100L);

        verify(repository).deleteById(100L);
        verify(repository).existsById(100L);
    }

    @Test
    void shouldThrowToDoListNotFoundExceptionWhenTryToGetAListNotExist(){
        when(repository.existsById(any(Long.class))).thenReturn(false);

        ToDoListNotFoundException exception = assertThrows(ToDoListNotFoundException.class, () -> mediator.deleteListById(100L));

        verify(repository, never()).deleteById(100L);
        verify(repository).existsById(100L);
        assertEquals("List not found", exception.getMessage());
    }

    @Test
    void shouldThrowToDoListNotFoundExceptionWhenTryToDeleteAListNotExist(){
        when(repository.existsById(any(Long.class))).thenReturn(false);

        ToDoListNotFoundException exception = assertThrows(ToDoListNotFoundException.class, () -> mediator.deleteListById(100L));

        verify(repository, never()).deleteById(100L);
        verify(repository).existsById(100L);
        assertEquals("List not found", exception.getMessage());


    }
}
