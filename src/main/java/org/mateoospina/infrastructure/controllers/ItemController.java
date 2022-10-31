package org.mateoospina.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.itemServices.ItemMediator;
import org.mateoospina.infrastructure.exception.ToDoListNotFoundException;
import org.mateoospina.infrastructure.mappers.ItemMapper;
import org.mateoospina.infrastructure.model.ItemDTO;
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
    public ResponseEntity<ItemDTO> GetNoteById(@PathVariable long itemId, @PathVariable long listId){
        Item item = itemMediator.getItemById(itemId);
        ItemDTO itemDTO = ItemMapper.itemToItemDTO(item);
        return ResponseEntity.ok().body(itemDTO);
    }

    @PostMapping(value="lists/{listId}/items")
    public ResponseEntity<?> saveNote(@Valid @RequestBody ItemDTO itemDTO, @PathVariable long listId){ // to do the mapping
        Item item = ItemMapper.itemDTOToItemForCreated(itemDTO);
        Item itemCreated = itemMediator.createItem(item);
        ItemDTO itemDTOCreated = ItemMapper.itemToItemDTO(itemCreated);
        return new ResponseEntity(itemDTOCreated, HttpStatus.CREATED);
    }

    @PatchMapping(path = "lists/{listId}/items/{itemId}", consumes = "application/json-patch+json")
    public ResponseEntity<ItemDTO> updateNote(@RequestBody JsonPatch patch, @PathVariable long itemId, @PathVariable long listId) throws JsonPatchException, JsonProcessingException {
        Item item = itemMediator.updateItem(patch, listId, itemId);
        ItemDTO itemDTO = ItemMapper.itemToItemDTO(item);
        return ResponseEntity.ok(itemDTO);
    }

    @DeleteMapping(value = "lists/{listId}/items/{itemId}")
    public ResponseEntity<?> deleteNote(@PathVariable ("itemId") Long itemId, @PathVariable long listId){
        itemMediator.deleteItemById(itemId);
        return ResponseEntity.ok().build();
    }
}
