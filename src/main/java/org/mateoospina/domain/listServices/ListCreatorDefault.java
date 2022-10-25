package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;


public class ListCreatorDefault implements ListCreator {

    private ListRepository listRepository;

    public ListCreatorDefault(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public ToDoList create(ToDoList toDoList){
        return listRepository.save(toDoList);
    }

}