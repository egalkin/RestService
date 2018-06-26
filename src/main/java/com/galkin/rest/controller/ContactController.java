package com.galkin.rest.controller;

import com.galkin.rest.model.Contact;
import com.galkin.rest.model.ApplicationDTO;
import com.galkin.rest.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<Contact> getAllClients() {
        return contactService.getAllContacts();
    }

    @PostMapping("/contacts")
    public Contact createClient(@Valid @RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @GetMapping("/contacts/{contactId}")
    public ApplicationDTO getClientLastApplication(@PathVariable Long contactId) {
        return contactService.getLastApplication(contactId);
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long contactId) {
        return contactService.deleteContact(contactId);
    }

}
