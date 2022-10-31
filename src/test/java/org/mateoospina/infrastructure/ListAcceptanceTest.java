package org.mateoospina.infrastructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.infrastructure.model.ToDoListsDTO;
import org.mateoospina.domain.persistence.ListRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //Use esto cuando solo desee configurar MockMvc
class ListAcceptanceTest {

    @Autowired
    private MockMvc mockMvc; //MockMvc es una clase de Spring que permite enviar solicitudes HTTP a los controladores y analizar la respuesta

    ObjectMapper objectMapper;

    ToDoListsDTO toDoListIn;

    ToDoList toDoListOut;

    @MockBean
    private ListRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext; //WebApplicationContext es una interfaz que proporciona acceso a la jerarquía de beans de la aplicación web

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//Configura MockMvc para usar el contexto de aplicación web

        toDoListOut = ToDoList.builder()
                .id(1L)
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .date(new Date(2023-06-29))
                .done(false)
                .build();

        toDoListIn = ToDoListsDTO.builder()
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("mateo@gmail.com")
                .date(new Date(2023-06-29))
                .done(false)
                .build();

        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
    }

    @Test
    void shouldCreateAListAndReturnStatusCode200() throws Exception {
        mockMvc.perform(post("/lists").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoListIn)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("1")));
    }

    @Test
    void shouldNotCreateAListAndReturnStatusCode400BecauseNameIsEmpty() throws Exception {
        toDoListIn.setName(null);

        mockMvc.perform(post("/lists").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoListIn)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name is empty")));
    }

    @Test
    void shouldNotCreateAListAndReturnStatusCode400BecauseEmailFormatIsNotValid() throws Exception {
        toDoListIn.setUser("mateo.com");

        mockMvc.perform(post("/lists").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoListIn)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("User(email) format is not valid")));
    }

    @Test
    void shouldGetAListSuccessfully() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(true);
        when(repository.findById(any(long.class))).thenReturn(java.util.Optional.ofNullable(toDoListOut));

        mockMvc.perform(get("/lists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cosas por hacer")));
    }

    @Test
    void shouldThrowAnExceptionWhenGetAListBecauseIdNotExist() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(false);

        mockMvc.perform(get("/lists/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("List not found")));
    }

    @Test
    void shouldDeleteAListSuccessfully() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(true);

        mockMvc.perform(delete("/lists/1"))
                .andExpect(status().isOk());

        verify(repository).deleteById(any(long.class));
    }

    @Test
    void shouldThrowAnExceptionWhenDeleteAListBecauseWithIdNotExist() throws Exception {
        when(repository.existsById(any(long.class))).thenReturn(false);

        mockMvc.perform(delete("/lists/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("List not found")));
    }

}
