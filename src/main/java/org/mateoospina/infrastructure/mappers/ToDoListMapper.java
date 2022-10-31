package org.mateoospina.infrastructure.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

import javax.validation.Valid;
import java.util.Calendar;

public class ToDoListMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ToDoListsDTO toDoListToToDoListDTO(ToDoList toDoList){
        ToDoListsDTO toDoListInfra = new ToDoListsDTO();
        toDoListInfra.setId(toDoList.getId());
        toDoListInfra.setName(toDoList.getName());
        toDoListInfra.setDescription(toDoList.getDescription());
        toDoListInfra.setUser(toDoList.getUser());
        toDoListInfra.setDate(Calendar.getInstance().getTime());
        toDoListInfra.setDone(toDoList.getDone());
        return toDoListInfra;
    }

    public static ToDoList toToDoListDTOToToDoListForCreated(ToDoListsDTO toDoListInfra){
        ToDoList toDoList = new ToDoList();
        toDoList.setName(toDoListInfra.getName());
        toDoList.setDescription(toDoListInfra.getDescription());
        toDoList.setDate(toDoListInfra.getDate());
        toDoList.setUser(toDoListInfra.getUser());
        toDoList.setDone(toDoListInfra.getDone());
        return toDoList;
    }


    public static ToDoList jsonPatchToToDoList(JsonPatch patch,ToDoList toDoList) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(toDoList, JsonNode.class));

        return objectMapper.treeToValue(patched, ToDoList.class);
    }
}