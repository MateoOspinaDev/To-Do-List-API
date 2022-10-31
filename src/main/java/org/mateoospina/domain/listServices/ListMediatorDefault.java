package org.mateoospina.domain.listServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
    public ToDoList getListById(long id) {
        verifyIfToDoListExists(id);
        return listRepository.findById(id).get();
    }

    @Override
    public void deleteListById(Long id) {
        verifyIfToDoListExists(id);
        listRepository.deleteById(id);
    }

    @Override
    public ToDoList updateList(JsonPatch patch, long listId) throws JsonPatchException, JsonProcessingException {
        verifyIfToDoListExists(listId);
        ToDoList toDoList = listRepository.findById(listId).get();
        ToDoList toDoListPatched = ToDoListMapper.jsonPatchToToDoList(patch, toDoList);
        this.createList(toDoListPatched);
        return toDoListPatched;
    }

    public void verifyIfToDoListExists(long id){
        if(!listRepository.existsById(id)) throw new ToDoListNotFoundException(TO_DO_LIST_NOT_FOUND);
    }
}