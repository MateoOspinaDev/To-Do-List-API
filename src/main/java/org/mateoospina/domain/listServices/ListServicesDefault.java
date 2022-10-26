package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

public class ListServicesDefault implements ListServices {

    private ListRepository listRepository;

    public ListServicesDefault(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ToDoListsDTO getNoteById(long id) {
        ToDoList list = listRepository.findById(id).orElseThrow(
                () -> new ToDoListNotFoundException("List with id: "+id+" not found")
        );
        return ToDoListMapper.toDoListDTO(list);
    }

    @Override
    public void deleteNote(Long id) {
        if(!listRepository.existsById(id)) {
            throw new ToDoListNotFoundException("List with id: "+id+" not exists, that's why it can't be removed.");
        }
        listRepository.deleteById(id);
    }

    public ToDoListsDTO findNoteById(Long id) {
        if(!listRepository.existsById(id)) {
            throw new ToDoListNotFoundException("List with id: "+id+" not exists, that's why it can't be removed.");
        }
        return ToDoListMapper.toDoListDTO(listRepository.findById(id).get());
    }
}
