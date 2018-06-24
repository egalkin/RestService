package com.galkin.rest;


import com.galkin.rest.controller.ContactController;
import com.galkin.rest.exception.NotFoundException;
import com.galkin.rest.model.Application;
import com.galkin.rest.model.Contact;
import com.galkin.rest.model.RequestAnswer;
import com.galkin.rest.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Date;
import java.util.Arrays;


public class ContactControllerTests {
    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(contactController)
                .build();
    }

    @Test
    public void testGetAllContactsId() throws Exception {
        List<Contact> contacts = Arrays.asList(new Contact(25), new Contact(26), new Contact(46), new Contact(99));
        when(contactService.getAllContacts()).thenReturn(contacts);
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].CONTACT_ID", is(25)))
                .andExpect(jsonPath("$[1].CONTACT_ID", is(26)))
                .andExpect(jsonPath("$[2].CONTACT_ID", is(46)))
                .andExpect(jsonPath("$[3].CONTACT_ID", is(99)));
        verify(contactService, times(1)).getAllContacts();
        verifyNoMoreInteractions(contactService);
    }

    @Test
    public void testGetLastApplicationButNotAnyApplicationsExistOrNotContactExist() throws Exception {
        when(contactService.getLastApplication(1l)).thenThrow(new NotFoundException());
        mockMvc.perform(get("/contacts/{id}", 1L))
                .andExpect(status().isNotFound());
        verify(contactService, times(1)).getLastApplication(1L);
        verifyNoMoreInteractions(contactService);
    }

    @Test
    public void testGetLastApplication() throws Exception {
        Application application = new Application(27L ,"Book", new Date());
        RequestAnswer answer = new RequestAnswer(99L, application);
        when(contactService.getLastApplication(99L)).thenReturn(answer);
        mockMvc.perform(get("/contacts/99"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.CONTACT_ID", is(answer.getContactId())))
                .andExpect(jsonPath("$.APPLICATION_ID", is(answer.getApplicationId())))
                .andExpect(jsonPath("$.DT_CREATED", is(answer.getDtCreated())))
                .andExpect(jsonPath("$.PRODUCT_NAME", is(answer.getProductName())));
        verify(contactService, times(1)).getLastApplication(99L);
        verifyNoMoreInteractions(contactService);
    }




}
