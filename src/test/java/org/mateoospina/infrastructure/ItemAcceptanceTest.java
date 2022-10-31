package org.mateoospina.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.Item;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.persistence.ItemRepository;
import org.mateoospina.domain.persistence.ListRepository;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemAcceptanceTest {
    @Autowired
    private MockMvc mockMvc; //MockMvc es una clase de Spring que permite enviar solicitudes HTTP a los controladores y analizar la respuesta

    ObjectMapper objectMapper;

    Item itemIn;

    Item itemOut;

    @MockBean
    private ItemRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext; //WebApplicationContext es una interfaz que proporciona acceso a la jerarquía de beans de la aplicación web

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//Configura MockMvc para usar el contexto de aplicación web

        itemOut = Item.builder()
                .id(1L)
                .description("Una de las cosas por hacer")
                .done(false)
                .build();

        itemIn = Item.builder()
                .description("Una de las cosas por hacer")
                .done(false)
                .build();

        openMocks(this);
        when(repository.save(any(Item.class))).thenReturn(itemOut);
    }

    @Test
    void shouldCreateAItemAndReturnStatusCode200() throws Exception {
        mockMvc.perform(post("/lists/1/items").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemIn)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("1")));
    }

    @Test
    void shouldNotCreateAItemAndReturnStatusCode400BecauseDescriptionIsEmpty() throws Exception {
        itemIn.setDescription(null);

        mockMvc.perform(post("/lists/1/items").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemIn)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Description is empty")));
    }

    @Test
    void shouldGetAItemSuccessfully() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(true);
        when(repository.findById(any(long.class))).thenReturn(java.util.Optional.ofNullable(itemOut));

        mockMvc.perform(get("/lists/1/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Una de las cosas por hacer")));
    }

    @Test
    void shouldThrowAnExceptionWhenGetAListBecauseIdNotExist() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(false);

        mockMvc.perform(get("/lists/1/items/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Item not found")));
    }

    @Test
    void shouldDeleteAItemSuccessfully() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(true);

        mockMvc.perform(delete("/lists/1/items/1"))
                .andExpect(status().isOk());

        verify(repository).deleteById(any(long.class));
    }

    @Test
    void shouldThrowAnExceptionWhenDeleteAItemBecauseWithIdNotExist() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(false);

        mockMvc.perform(delete("/lists/1/items/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Item not found")));
    }
}
