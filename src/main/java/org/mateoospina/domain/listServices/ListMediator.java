package org.mateoospina.domain.listServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

public interface ListMediator {
    ToDoList createList(ToDoList toDoList);
    ToDoList getListById(long id);
    void deleteListById(Long id);
    ToDoList updateList(JsonPatch patch, long listId) throws JsonPatchException, JsonProcessingException;
}