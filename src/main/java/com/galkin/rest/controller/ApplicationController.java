package com.galkin.rest.controller;

import com.galkin.rest.model.Application;
import com.galkin.rest.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/contacts/{contactId}/applications")
    public Set<Application> getAllApplicationsByContactId(@PathVariable (value = "contactId") Long contactId) {
        return applicationService.getAllApplications(contactId);

    }

    @GetMapping("/contacts/{contactId}/applications/{applicationId}")
    public Application getApplication(@PathVariable (value = "contactId") Long contactId,
                                      @PathVariable (value = "applicationId") Long applicationId) {
        return applicationService.getApplication(contactId, applicationId);
    }

    @PostMapping("/contacts/{contactId}/applications")
    public Application createApplication(@PathVariable (value = "contactId") Long contactId,
                                         @Valid @RequestBody Application application) {
        return applicationService.createApplication(contactId, application);
    }

    @PutMapping("/contacts/{contactId}/applications/{applicationId}")
    public Application updateApplication(@PathVariable (value = "contactId") Long contactId,
                                         @PathVariable (value = "applicationId") Long applicationId,
                                         @Valid @RequestBody Application applicationRequest) {
        return applicationService.updateApplication(contactId, applicationId, applicationRequest);
    }

    @DeleteMapping("/contacts/{contactId}/applications/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable (value = "contactId") Long contactId,
                                               @PathVariable (value = "applicationId") Long applicationId) {
        return applicationService.deleteApplication(contactId, applicationId);
    }
}
