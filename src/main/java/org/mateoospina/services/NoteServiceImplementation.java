package org.mateoospina.services;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImplementation implements INoteService {

    @Autowired
    private ListRepository noteRepository;

    @Override
    public java.util.List getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public ToDoList saveNote(ToDoList toDoList) {
        return noteRepository.save(toDoList);
    }

    @Override
    public ToDoList updateNote(ToDoList toDoList) {
        return noteRepository.save(toDoList);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
