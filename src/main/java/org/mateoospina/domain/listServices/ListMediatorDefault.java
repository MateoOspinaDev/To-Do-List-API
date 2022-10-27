package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;


public class ListMediatorDefault implements ListMediator {

    private final ListRepository listRepository;

    public ListMediatorDefault(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ToDoList createList(ToDoList toDoList){
        return listRepository.save(toDoList);
    }

    @Override
    public ToDoListsDTO getListById(long id) {
        ToDoList list = listRepository.findById(id).orElseThrow(
                () -> new ToDoListNotFoundException("List with id: "+id+" not found")
        );
        return ToDoListMapper.toDoListDTO(list);
    }

    @Override
    public void deleteListById(Long id) {
        if(!listRepository.existsById(id)) {
            throw new ToDoListNotFoundException("List with id: "+id+" not exists, that's why it can't be removed.");
        }
        listRepository.deleteById(id);
    }
}