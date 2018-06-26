package com.galkin.rest.service;

import com.galkin.rest.exception.NotFoundException;
import com.galkin.rest.model.Application;
import com.galkin.rest.model.ApplicationDTO;
import com.galkin.rest.model.Contact;
import com.galkin.rest.repository.ApplicationRepository;
import com.galkin.rest.repository.ContactRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ApplicationRepository applicationRepository;

    public ContactService(ContactRepository contactRepository, ApplicationRepository applicationRepository) {
        this.contactRepository = contactRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public ApplicationDTO getLastApplication(Long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + " not found.");
        }
        List<Application> answer = applicationRepository.findByContactId(contactId, PageRequest.of(0,1,Sort.Direction.DESC, "dtCreated"));
        if (answer.isEmpty()) {
            throw new NotFoundException("Applications for CONTACT_ID " + contactId + " not found.");
        }
        return new ApplicationDTO(contactId, answer.get(0));
    }

    public ResponseEntity<?> deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
        return ResponseEntity.ok().build();
    }

}
