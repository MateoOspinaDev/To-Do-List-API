package org.mateoospina.domain.itemServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.persistence.ItemRepository;
import org.mateoospina.infrastructure.exception.ItemNotFoundException;
import org.mateoospina.infrastructure.mappers.ItemMapper;

public class ItemMediatorDefault implements ItemMediator {

    private final ItemRepository itemRepository;

    private final String ITEM_NOT_FOUND = "Item not found";

    public ItemMediatorDefault(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(long id) {
        verifyIfItemExists(id);
        return itemRepository.findById(id).get();
    }

    @Override
    public void deleteItemById(Long id) {
        verifyIfItemExists(id);
        itemRepository.deleteById(id);
    }

    @Override
    public Item updateItem(JsonPatch patch, long listId, long itemId) throws JsonPatchException, JsonProcessingException {
        verifyIfItemExists(itemId);
        Item item = itemRepository.findById(itemId).get();
        Item itemPatched = ItemMapper.jsonPatchToToDoList(patch, item);
        this.createItem(itemPatched);
        return itemPatched;
    }

    public void verifyIfItemExists(long id){
        if(!itemRepository.existsById(id)) throw new ItemNotFoundException(ITEM_NOT_FOUND);
    }
}