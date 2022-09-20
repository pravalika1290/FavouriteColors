package com.example.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.model.Persons;
import com.example.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PersonsController.class})
@ExtendWith(SpringExtension.class)
class PersonsControllerTest {
    @MockBean
    private PersonService personService;

    @Autowired
    private PersonsController personsController;

    /**
     * Method under test: {@link PersonsController#addPerson(Persons)}
     */
    @Test
    void testAddPerson() throws Exception {
        doNothing().when(personService).savePerson((Persons) any());

        Persons persons = new Persons();
        persons.setCity("Oxford");
        persons.setColor("Color");
        persons.setId(1);
        persons.setLastName("Doe");
        persons.setName("Name");
        persons.setZipcode("21654");
        String content = (new ObjectMapper()).writeValueAsString(persons);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/persons/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link PersonsController#getAllPersons()}
     */
    @Test
    void testGetAllPersons() throws Exception {
        when(personService.getAllPersons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/");
        MockMvcBuilders.standaloneSetup(personsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PersonsController#getPersonById(int)}
     */
    @Test
    void testGetPersonById() throws Exception {
        Persons persons = new Persons();
        persons.setCity("Oxford");
        persons.setColor("Color");
        persons.setId(1);
        persons.setLastName("Doe");
        persons.setName("Name");
        persons.setZipcode("21654");
        when(personService.getPersonById(anyInt())).thenReturn(persons);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/{id}", 1);
        MockMvcBuilders.standaloneSetup(personsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"lastName\":\"Doe\",\"zipcode\":\"21654\",\"city\":\"Oxford\",\"color\":\"Color\"}"));
    }

    /**
     * Method under test: {@link PersonsController#getPersonsByColor(String)}
     */
    @Test
    void testGetPersonsByColor() throws Exception {
        when(personService.getPersonsByColor((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/color/{color}", "Color");
        MockMvcBuilders.standaloneSetup(personsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

}

