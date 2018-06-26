package com.galkin.rest.controller;

import com.galkin.rest.model.Application;
import com.galkin.rest.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class ApplicationController {


    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/contacts/{contactId}/applications")
    public List<Application> getAllApplicationsByContactId(@PathVariable (value = "contactId") Long contactId,
                                              Pageable pageable) {
        return applicationService.getAllApplications(contactId, pageable);

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
