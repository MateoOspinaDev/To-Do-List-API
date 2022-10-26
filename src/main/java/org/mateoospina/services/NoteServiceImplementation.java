package org.mateoospina.services;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImplementation implements INoteService {

    @Autowired
    private ListRepository noteRepository;

    @Override
    public ToDoListsDTO getNoteById(long id) {
        ToDoList list = noteRepository.findById(id).orElseThrow(
                () -> new ToDoListNotFoundException("List with id:"+id+" not found")
        );
        return ToDoListMapper.toDoListDTO(list);
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
