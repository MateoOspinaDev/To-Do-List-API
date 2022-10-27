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

    @Autowired
    private ListMediator listMediator;

    @GetMapping(value="/{listId}")
    public ResponseEntity<ToDoListsDTO> GetNoteById(@PathVariable long listId){
        ToDoListsDTO listList = listMediator.getListById(listId);
        return ResponseEntity.ok().body(listList);
    }

    @PostMapping
    public ResponseEntity<?> saveNote(@Valid @RequestBody ToDoListsDTO toDoListsDTO){ // to do the mapping
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListsDTO);
            ToDoList toDoListCreated = listMediator.createList(toDoListToCreate);
            ToDoListsDTO toDoListDTOCreated = ToDoListMapper.toDoListDTO(toDoListCreated);
            return new ResponseEntity(toDoListDTOCreated, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{listId}", consumes = "application/json-patch+json")
    public ResponseEntity<ToDoListsDTO> updateNote(@RequestBody JsonPatch patch, @PathVariable long listId){
        try {
            ToDoListsDTO toDoListsDTO = listMediator.getListById(listId);
            ToDoListsDTO toDoListsDTOPatched = ToDoListMapper.jsonPatchToToDoList(patch, toDoListsDTO);
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListsDTOPatched);
            listMediator.createList(toDoListToCreate);
            return ResponseEntity.ok(toDoListsDTOPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ToDoListNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/{listId}")
    public ResponseEntity<?> deleteNote(@PathVariable ("listId") Long listId){
        listMediator.deleteListById(listId);
        return ResponseEntity.ok().build();
    }
}
