package org.mateoospina.domain.itemServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.Item;
import org.mateoospina.infrastructure.model.ItemDTO;

public interface ItemMediator {
    Item createItem(Item item);
    Item getItemById(long id);
    void deleteItemById(Long id);
    Item updateItem(JsonPatch patch, long listId, long itemId) throws JsonPatchException, JsonProcessingException;
}