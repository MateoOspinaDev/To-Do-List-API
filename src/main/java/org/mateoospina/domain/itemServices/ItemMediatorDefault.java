package org.mateoospina.domain.itemServices;

import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.persistence.ItemRepository;
import org.mateoospina.infrastructure.exception.ItemNotFoundException;

public class ItemMediatorDefault implements ItemMediator {

    private final ItemRepository itemRepository;

    public ItemMediatorDefault(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Item with id: "+id+" not found")
        );
    }

    @Override
    public void deleteItemById(Long id) {
        if(!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item with id: "+id+" not exists, that's why it can't be removed.");
        }
        itemRepository.deleteById(id);
    }
}