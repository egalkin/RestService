package com.galkin.rest.service;

import com.galkin.rest.exception.NotFoundException;
import com.galkin.rest.model.Application;
import com.galkin.rest.model.Contact;
import com.galkin.rest.model.RequestAnswer;
import com.galkin.rest.repository.ApplicationRepository;
import com.galkin.rest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Contact> getAllContacts() {
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        if (contacts.isEmpty())
            throw new NotFoundException("Contacts information not found.");
        return contacts;
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public RequestAnswer getLastApplication(Long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + " not found.");
        }
        TreeSet<Application> answer = applicationRepository.findByContactId(contactId);
        if (answer.isEmpty()) {
            throw new NotFoundException("Applications for CONTACT_ID " + contactId + " not found.");
        }
        return new RequestAnswer(contactId, answer.first());
    }

    public ResponseEntity<?> deleteContact(Long contactId) {
        return contactRepository.findById(contactId).map(Client -> {
            contactRepository.delete(Client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("CONTACT_ID " + contactId + " not found."));
    }

}
