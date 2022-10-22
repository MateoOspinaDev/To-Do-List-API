package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;

public interface ListCreator {
    ToDoList create(ToDoList toDoList);
}