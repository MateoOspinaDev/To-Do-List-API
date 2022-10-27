package org.mateoospina.infrastructure.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

import java.util.Calendar;

public class ToDoListMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

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

    public static ToDoListsDTO jsonPatchToToDoList(JsonPatch patch, ToDoListsDTO toDoListsDTO) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(toDoListsDTO, JsonNode.class));
        return objectMapper.treeToValue(patched, ToDoListsDTO.class);
    }
}