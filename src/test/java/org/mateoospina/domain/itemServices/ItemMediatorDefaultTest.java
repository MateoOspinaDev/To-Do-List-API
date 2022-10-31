package org.mateoospina.domain.itemServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.persistence.ItemRepository;
import org.mateoospina.infrastructure.exception.ItemNotFoundException;
import org.mockito.Mock;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ItemMediatorDefaultTest {
    @Mock
    private ItemRepository repository;

    private ItemMediator mediator;

    private Item itemIn;

    @BeforeEach
    public void setup(){
        itemIn = Item.builder()
                .description("Buscar en internet cuales son los mejores taladros")
                .done(false)
                .build();

        Item ItemOut = Item.builder()
                .id(100L)
                .description("Buscar en internet cuales son los mejores taladros")
                .done(false)
                .build();

        openMocks(this);
        when(repository.save(any(Item.class))).thenReturn(ItemOut);
        when(repository.findById(any(Long.class))).thenReturn(ofNullable(ItemOut));
        mediator = new ItemMediatorDefault(repository);
    }

    @Test
    void shouldCreateAItemSuccessful() {
        Item itemCreated = mediator.createItem(itemIn);

        assertEquals(100, itemCreated.getId());
        verify(repository).save(any(Item.class));
    }

    @Test
    void shouldGetAItemSuccessful() {
        when(repository.existsById(any(Long.class))).thenReturn(true);
        Item itemFound = mediator.getItemById(100L);

        assertEquals(100, itemFound.getId());
        verify(repository).findById(100L);
        verify(repository).existsById(100L);
    }

    @Test
    void shouldDeleteAItemSuccessful() {
        when(repository.existsById(any(Long.class))).thenReturn(true);
        mediator.deleteItemById(100L);

        verify(repository).deleteById(100L);
        verify(repository).existsById(100L);
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenTryToGetAItemNotExist(){
        when(repository.existsById(any(Long.class))).thenReturn(false);

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> mediator.getItemById(100L));

        assertEquals("Item not found", exception.getMessage());
        verify(repository).existsById(100L);
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenTryToDeleteAItemNotExist(){
        when(repository.existsById(any(Long.class))).thenReturn(false);

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> mediator.getItemById(100L));

        assertEquals("Item not found", exception.getMessage());
        verify(repository).existsById(100L);
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenTryToPatchAItemNotExist(){
        when(repository.existsById(any(Long.class))).thenReturn(false);

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> mediator.getItemById(100L));

        assertEquals("Item not found", exception.getMessage());
        verify(repository).existsById(100L);
    }
}
