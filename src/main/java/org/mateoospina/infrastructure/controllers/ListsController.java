package org.mateoospina.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.listServices.ListMediator;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lists")
public class ListsController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ListMediator listMediator;

    @GetMapping(value="/{id}")
    public ResponseEntity<ToDoListsDTO> GetNoteById(@PathVariable long id){
        ToDoListsDTO listList = listMediator.getNoteById(id);
        return ResponseEntity.ok().body(listList);
    }

    @PostMapping
    public ResponseEntity<?> saveNote(@Valid @RequestBody ToDoListsDTO toDoListsDTO){ // to do the mapping
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListsDTO);
            ToDoList toDoListCreated = listMediator.create(toDoListToCreate);
            ToDoListsDTO toDoListDTOCreated = ToDoListMapper.toDoListDTO(toDoListCreated);
            return new ResponseEntity(toDoListDTOCreated, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<ToDoListsDTO> updateNote(@RequestBody JsonPatch patch, @PathVariable long id){
        try {
            ToDoListsDTO toDoListsDTO = listMediator.getNoteById(id);
            ToDoListsDTO toDoListsDTOPatched = jsonPatchToToDoList(patch, toDoListsDTO);
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListsDTOPatched);
            listMediator.create(toDoListToCreate);
            return ResponseEntity.ok(toDoListsDTOPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ToDoListNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private ToDoListsDTO jsonPatchToToDoList(JsonPatch patch, ToDoListsDTO toDoListsDTO) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(toDoListsDTO, JsonNode.class));
        return objectMapper.treeToValue(patched, ToDoListsDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable ("id") Long id){
        listMediator.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
