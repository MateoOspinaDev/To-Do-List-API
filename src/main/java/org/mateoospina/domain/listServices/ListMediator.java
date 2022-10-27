package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

public interface ListMediator {
    ToDoList create(ToDoList toDoList);
    ToDoListsDTO getNoteById(long id);
    void deleteNote(Long id);
}