package org.mateoospina.services;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INoteService {
    ToDoListsDTO getNoteById(long id);
    ToDoList saveNote(ToDoList toDoList);
    ToDoList updateNote(ToDoList toDoList);
    void deleteNote(Long id);
}

