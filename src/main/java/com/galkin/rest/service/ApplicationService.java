package com.galkin.rest.service;

import com.galkin.rest.exception.NotFoundException;
import com.galkin.rest.model.Application;
import com.galkin.rest.repository.ApplicationRepository;
import com.galkin.rest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ContactRepository contactRepository) {
        this.applicationRepository = applicationRepository;
        this.contactRepository = contactRepository;
    }

    public List<Application> getAllApplications(Long contactId, Pageable pageable) {
        return applicationRepository.findByContactId(contactId, pageable);
    }

    public Application getApplication(Long contactId, Long applicationId) {
        if (!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + " not found.");
        }
        return applicationRepository.findById(applicationId).orElseThrow(() -> new NotFoundException("Application with given APPLICATION_ID " + applicationId + " not found."));
    }

    public Application createApplication(Long contactId, Application application) {
        return contactRepository.findById(contactId).map(contact -> {
            application.setContact(contact);
            return applicationRepository.save(application);
        }).orElseThrow(() -> new NotFoundException("CONTACT_ID " + contactId + " not found."));
    }

    public Application updateApplication(Long contactId, Long applicationId, Application applicationRequest) {
        if(!contactRepository.existsById(contactId)) {
            throw new NotFoundException("CONTACT_ID " + contactId + "not found.");
        }
        return applicationRepository.findById(applicationId).map(application -> {
            application.setProductName(applicationRequest.getProductName());
            application.setDtCreated(new Date());
            return applicationRepository.save(application);
        }).orElseThrow(() -> new NotFoundException("Application with given APPLICATION_ID " + applicationId + " not found."));
    }

    public ResponseEntity<?> deleteApplication(Long contactId, Long applicationId) {
        if (contactRepository.existsById(contactId)) {
            applicationRepository.deleteById(applicationId);
        }
        return ResponseEntity.ok().build();
    }
}
