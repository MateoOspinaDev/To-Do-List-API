package org.mateoospina.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateoospina.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc //Use esto cuando solo desee configurar MockMvc
class NoteTest {

    @Autowired
    private MockMvc mockMvc; //MockMvc es una clase de Spring que permite enviar solicitudes HTTP a los controladores y analizar la respuesta

    @MockBean
    private INoteService noteService;

    @Autowired
    private WebApplicationContext webApplicationContext; //WebApplicationContext es una interfaz que proporciona acceso a la jerarquía de beans de la aplicación web

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//Configura MockMvc para usar el contexto de aplicación web
    }

    @Test
    void shouldGetAllNotes() throws Exception {
        when(noteService.getNotes()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/note"))
                .andExpect(status().isOk());
    }

}
