package com.galkin.rest;

import com.galkin.rest.controller.ContactController;
import com.galkin.rest.exception.NotFoundException;
import com.galkin.rest.model.Application;
import com.galkin.rest.model.ApplicationDTO;
import com.galkin.rest.model.Contact;
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
        List<Contact> contacts = Arrays.asList(new Contact(14L), new Contact(19L), new Contact(25L), new Contact(26L),
                                                new Contact(43L), new Contact(46L), new Contact(51L), new Contact(99L));
        when(contactService.getAllContacts()).thenReturn(contacts);
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].CONTACT_ID", is(14)))
                .andExpect(jsonPath("$[1].CONTACT_ID", is(19)))
                .andExpect(jsonPath("$[2].CONTACT_ID", is(25)))
                .andExpect(jsonPath("$[3].CONTACT_ID", is(26)))
                .andExpect(jsonPath("$[4].CONTACT_ID", is(43)))
                .andExpect(jsonPath("$[5].CONTACT_ID", is(46)))
                .andExpect(jsonPath("$[6].CONTACT_ID", is(51)))
                .andExpect(jsonPath("$[7].CONTACT_ID", is(99)));
        verify(contactService, times(1)).getAllContacts();
        verifyNoMoreInteractions(contactService);
    }

    @Test
    public void testGetLastApplicationButNotAnyApplicationsExistOrNotContactExist() throws Exception {
        when(contactService.getLastApplication(1L)).thenThrow(new NotFoundException());
        mockMvc.perform(get("/contacts/{id}", 1L))
                .andExpect(status().isNotFound());
        verify(contactService, times(1)).getLastApplication(1L);
        verifyNoMoreInteractions(contactService);
    }

    @Test
    public void testGetLastApplication() throws Exception {
        Application application = new Application(27L ,"Book", new Date());
        ApplicationDTO answer = new ApplicationDTO(99L, application);
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
