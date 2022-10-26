package org.mateoospina.infrastructure.mappers;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ToDoListMapper {

    public static ToDoList toToDoList(ToDoListsDTO toDoListInfra){
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListInfra.getId());
        toDoList.setName(toDoListInfra.getName());
        toDoList.setDescription(toDoListInfra.getDescription());
        toDoList.setUser(toDoListInfra.getUser());
        toDoList.setCompleted(toDoListInfra.getCompleted());
        return toDoList;
    }

    public static ToDoListsDTO toDoListDTO(ToDoList toDoList){
        ToDoListsDTO toDoListInfra = new ToDoListsDTO();
        toDoListInfra.setId(toDoList.getId());
        toDoListInfra.setName(toDoList.getName());
        toDoListInfra.setDescription(toDoList.getDescription());
        toDoListInfra.setUser(toDoList.getUser());
        toDoListInfra.setDate(Calendar.getInstance().getTime());
        toDoListInfra.setCompleted(toDoList.getCompleted());
        return toDoListInfra;
    }
}