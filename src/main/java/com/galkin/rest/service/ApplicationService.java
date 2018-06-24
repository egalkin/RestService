package com.galkin.rest.service;

import com.galkin.rest.exception.NotFoundException;
import com.galkin.rest.model.Application;
import com.galkin.rest.repository.ApplicationRepository;
import com.galkin.rest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContactRepository contactRepository;

    public Set<Application> getAllApplications(Long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + " not found.");
        }
        Set<Application> applications = applicationRepository.findByContactId(contactId);
        if (applications.isEmpty()) {
            throw new NotFoundException("Applications for CONTACT_ID " + contactId + " not found.");
        }
        return applicationRepository.findByContactId(contactId);
    }

    public Application getApplication(Long contactId, Long applicationId) {
        if (!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + " not found.");
        }
        return applicationRepository.findById(applicationId).orElseThrow(() -> new NotFoundException("Application with given APPLICATION_ID " + applicationId + " not found."));
    }

    public Application createApplication(Long contactId, Application application) {
        if (!contactRepository.existsById(contactId))
            throw new NotFoundException("CONTACT_ID " + contactId + " not found.");
        return contactRepository.findById(contactId).map(contact -> {
            application.setContact(contact);
            return applicationRepository.save(application);
        }).orElseThrow(NotFoundException::new);
    }

    public Application updateApplication(Long contactId, Long applicationId, Application applicationRequest) {
        if(!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + "not found.");
        }
        return applicationRepository.findById(applicationId).map(Application -> {
            Application.setProductName(applicationRequest.getProductName());
            return applicationRepository.save(Application);
        }).orElseThrow(() -> new NotFoundException("Application with given APPLICATION_ID " + applicationId + " not found."));
    }

    public ResponseEntity<?> deleteApplication(Long contactId, Long applicationId) {
        if(!contactRepository.existsById(contactId)) {
            throw new NotFoundException();
        }
        return applicationRepository.findById(applicationId).map(application -> {
            applicationRepository.delete(application);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("Application with given APPLICATION_ID " + applicationId +  "not found."));
    }
}
