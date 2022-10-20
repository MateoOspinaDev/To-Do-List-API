package org.mateoospina.services;

import org.mateoospina.entities.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INoteService {
    List<Note> getNotes();
    Note saveNote(Note note);
    Note updateNote(Note note);
    void deleteNote(Long id);
}
