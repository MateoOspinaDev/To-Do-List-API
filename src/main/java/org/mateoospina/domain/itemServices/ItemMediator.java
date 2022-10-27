package org.mateoospina.domain.itemServices;

import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;

public interface ItemMediator {
    Item createItem(Item item);
    Item getItemById(long id);
    void deleteItemById(Long id);
}