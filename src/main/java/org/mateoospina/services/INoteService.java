package org.mateoospina.services;

import org.mateoospina.domain.entities.ToDoList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INoteService {
    List<ToDoList> getNotes();
    ToDoList saveNote(ToDoList toDoList);
    ToDoList updateNote(ToDoList toDoList);
    void deleteNote(Long id);
}
