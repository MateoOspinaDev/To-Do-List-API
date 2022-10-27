package org.mateoospina.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.itemServices.ItemMediator;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ItemMapper;
import org.mateoospina.infrastructure.mappers.ToDoListMapper;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ItemController {
    @Autowired
    private ItemMediator itemMediator;

    @GetMapping(value="lists/{listId}/items/{itemId}")
    public ResponseEntity<Item> GetNoteById(@PathVariable long itemId, @PathVariable long listId){
        Item item = itemMediator.getItemById(itemId);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping(value="lists/{listId}/items")
    public ResponseEntity<?> saveNote(@Valid @RequestBody Item item, @PathVariable long listId){ // to do the mapping
        Item itemCreated = itemMediator.createItem(item);
        return new ResponseEntity(itemCreated, HttpStatus.CREATED);
    }

    @PatchMapping(path = "lists/{listId}/items/{itemId}", consumes = "application/json-patch+json")
    public ResponseEntity<Item> updateNote(@RequestBody JsonPatch patch, @PathVariable long itemId, @PathVariable long listId){
        try {
            Item item = itemMediator.getItemById(itemId);
            Item itemPatched = ItemMapper.jsonPatchToToDoList(patch, item);
            itemMediator.createItem(itemPatched);
            return ResponseEntity.ok(itemPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ToDoListNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "lists/{listId}/items/{itemId}")
    public ResponseEntity<?> deleteNote(@PathVariable ("itemId") Long itemId, @PathVariable long listId){
        itemMediator.deleteItemById(itemId);
        return ResponseEntity.ok().build();
    }
}
