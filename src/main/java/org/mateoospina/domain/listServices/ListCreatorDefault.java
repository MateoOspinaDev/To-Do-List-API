package org.mateoospina.domain.listServices;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ListRepository;

import static org.mateoospina.domain.util.StringUtils.*;

public class ListCreatorDefault implements ListCreator {

    private ListRepository listRepository;

    public ListCreatorDefault(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public ToDoList create(ToDoList toDoList){
        validate(toDoList);
        return listRepository.save(toDoList);
    }

    private void validate(ToDoList toDoList) {
        StringBuilder details = new StringBuilder();
        if(isNullOrBlank(toDoList.getName())) details.append("Name is empty").append(System.lineSeparator());
        if(isNullOrBlank(toDoList.getUser())) details.append("User is empty").append(System.lineSeparator());
        if(isNotNullOrBlank(toDoList.getUser()) &&
                isNotEmail(toDoList.getUser())) details.append("The user does not have the email format");

        if(details.length() != 0)
            throw new IllegalArgumentException(details.toString());
    }

}