package com.galkin.rest.controller;

import com.galkin.rest.model.Contact;
import com.galkin.rest.model.RequestAnswer;
import com.galkin.rest.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/contacts")
    public List<Contact> getAllClients() {
        return contactService.getAllContacts();
    }

    @PostMapping("/contacts")
    public Contact createClient(@Valid @RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @GetMapping("/contacts/{contactId}")
    public RequestAnswer getClientLastApplication(@PathVariable Long contactId) {
        return contactService.getLastApplication(contactId);
    }

    @PutMapping("/contacts/{contactId}")
    public Contact updateContact(@PathVariable Long contactId, @RequestBody Contact contactRequest) {
        return contactService.updateContact(contactId, contactRequest);
    }


    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long contactId) {
        return contactService.deleteContact(contactId);
    }

}
