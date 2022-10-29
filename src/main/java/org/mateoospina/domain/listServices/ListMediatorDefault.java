package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;


public class ListMediatorDefault implements ListMediator {

    private final ListRepository listRepository;

    private final String TO_DO_LIST_NOT_FOUND = "List not found";

    public ListMediatorDefault(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public ToDoList createList(ToDoList toDoList){
        return listRepository.save(toDoList);
    }

    @Override
    public ToDoListsDTO getListById(long id) {
        verifyIfToDoListExists(id);
        ToDoList list = listRepository.findById(id).get();
        return ToDoListMapper.toDoListDTO(list);
    }

    @Override
    public void deleteListById(Long id) {
        verifyIfToDoListExists(id);
        listRepository.deleteById(id);
    }

    public void verifyIfToDoListExists(long id){
        if(!listRepository.existsById(id)) throw new ToDoListNotFoundException(TO_DO_LIST_NOT_FOUND);
    }
}