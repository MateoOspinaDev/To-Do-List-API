package org.mateoospina.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/lists")
public class ListsController {

    @Autowired
    private ListMediator listMediator;

    @GetMapping(value="/{listId}")
    public ResponseEntity<ToDoListsDTO> GetNoteById(@PathVariable long listId){
        ToDoList listList = listMediator.getListById(listId);
        ToDoListsDTO listDTO = ToDoListMapper.toDoListToToDoListDTO(listList);
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<?> saveNote(@Valid @RequestBody ToDoListsDTO toDoListsDTO){
        ToDoList list = ToDoListMapper.toToDoListDTOToToDoListForCreated(toDoListsDTO);
        ToDoList listCreated = listMediator.createList(list);
        ToDoListsDTO listDTOCreated = ToDoListMapper.toDoListToToDoListDTO(listCreated);
        return new ResponseEntity(listDTOCreated, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{listId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateNote(@RequestBody JsonPatch patch, @PathVariable long listId) throws JsonPatchException, JsonProcessingException {
        ToDoList list = listMediator.updateList(patch, listId);
        ToDoListsDTO listDTO = ToDoListMapper.toDoListToToDoListDTO(list);
        return ResponseEntity.ok(listDTO);
    }

    @DeleteMapping(value = "/{listId}")
    public ResponseEntity<?> deleteNote(@PathVariable ("listId") Long listId){
        listMediator.deleteListById(listId);
        return ResponseEntity.ok().build();
    }
}
