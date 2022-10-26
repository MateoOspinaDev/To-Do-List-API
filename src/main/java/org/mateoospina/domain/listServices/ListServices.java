package org.mateoospina.domain.listServices;

import org.mateoospina.infrastructure.model.ToDoListsDTO;

public interface ListServices {
    ToDoListsDTO getNoteById(long id);
    void deleteNote(Long id);
}
