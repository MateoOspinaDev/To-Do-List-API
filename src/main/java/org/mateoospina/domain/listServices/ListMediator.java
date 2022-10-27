package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

public interface ListMediator {
    ToDoList createList(ToDoList toDoList);
    ToDoListsDTO getListById(long id);
    void deleteListById(Long id);

}