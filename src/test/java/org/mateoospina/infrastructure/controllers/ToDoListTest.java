package org.mateoospina.infrastructure.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.domain.entities.ToDoList;
import org.mateoospina.domain.listServices.ListMediator;
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
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //Use esto cuando solo desee configurar MockMvc
class ToDoListTest {

    @Autowired
    private MockMvc mockMvc; //MockMvc es una clase de Spring que permite enviar solicitudes HTTP a los controladores y analizar la respuesta

    ObjectMapper objectMapper;

    @MockBean
    private ListRepository repository;

    @MockBean
    private ListMediator noteService;

    @Autowired
    private WebApplicationContext webApplicationContext; //WebApplicationContext es una interfaz que proporciona acceso a la jerarquía de beans de la aplicación web

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//Configura MockMvc para usar el contexto de aplicación web
        ToDoList toDoListOut = ToDoList.builder()
                .id(100L)
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();
        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
    }

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

//    @Test
//    void shouldCreateAListAndReturnStatusCode200() throws Exception {
//        ToDoListsDTO toDoListInfra = ToDoListsDTO.builder()
//                .id(1L)
//                .name("Cosas por hacer")
//                .description("Mis cosas por hacer esta semana")
//                .user("cdanielmg200@gmail.com")
//                .date(new Date(2023-06-29))
//                .build();
//
//        mockMvc.perform(post("/lists").contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(toDoListInfra)))
//                .andExpect(status().isCreated())
//                .andExpect(content().string(containsString("100")));
//    }

    @Test
    void shouldNotCreateAListAndReturnStatusCode400() throws Exception {
        ToDoListsDTO toDoListInfra = ToDoListsDTO.builder()
                .name(null)
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .date(new Date(2023-06-29))
                .build();

        mockMvc.perform(post("/lists").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDoListInfra)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name is empty")));
    }

}
