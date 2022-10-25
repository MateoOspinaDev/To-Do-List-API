package org.mateoospina.infrastructure.controllers;

import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.listServices.ListCreator;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.mateoospina.infrastructure.model.Error;
import org.mateoospina.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lists")
public class ListsController {

    @Autowired
    private INoteService noteService;

    @Autowired
    private ListCreator listCreator;

    @GetMapping
    public ResponseEntity<java.util.List> GetNotes(){
        java.util.List listList = noteService.getNotes();
        return ResponseEntity.ok().body(listList);
    }

    @PostMapping
    public ResponseEntity<?> saveNote(@Valid @RequestBody ToDoListsDTO toDoListsDTO){ // to do the mapping
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListsDTO);
            ToDoList toDoListCreated = listCreator.create(toDoListToCreate);
            ToDoListsDTO toDoListDTOCreated = ToDoListMapper.ToDoListInfra(toDoListCreated);
            return new ResponseEntity(toDoListDTOCreated, HttpStatus.CREATED);
    }

    @PutMapping(params = "noteId")
    public ResponseEntity<ToDoList> updateNote(@RequestBody ToDoList toDoList, @RequestParam String noteId){
        return ResponseEntity.ok().body(noteService.updateNote(toDoList));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable ("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
