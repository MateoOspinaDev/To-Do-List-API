package org.mateoospina.controllers;

import lombok.RequiredArgsConstructor;
import org.mateoospina.entities.Note;
import org.mateoospina.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private INoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> GetNotes(){
        List<Note> listNote = noteService.getNotes();
        return ResponseEntity.ok().body(listNote);
    }

    @PostMapping
    public ResponseEntity<Note> saveNote(@RequestBody Note note){
        return ResponseEntity.ok().body(noteService.saveNote(note));
    }

    @PutMapping(params = "noteId")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @RequestParam String noteId){
        return ResponseEntity.ok().body(noteService.updateNote(note));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable ("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
