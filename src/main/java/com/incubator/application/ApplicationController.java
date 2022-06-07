package com.incubator.application;

import com.incubator.exceptions.ApplicationNotFound;
import com.incubator.exceptions.InvalidStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://cors-everywhere-me.herokuapp.com/http://ec2-18-216-140-13.us-east-2.compute.amazonaws.com:8080/", "http://localhost:3000/"})
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Object> createsApplication (@RequestBody HashMap<String, Object> userMap) {
        return applicationService.createApplication(userMap);
    }

    @PostMapping("/multiple")
    public ResponseEntity<Object> createsApplications (@RequestBody Iterable<Application> applicationList) {
        return applicationService.createApplications(applicationList);
    }

    @GetMapping
    public ResponseEntity<Object> getAllApplications() {
        return applicationService.readApplications();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplication(@PathVariable Long id) throws ApplicationNotFound {
        return applicationService.deleteApplication(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readApplicationById(@PathVariable Long id) throws ApplicationNotFound {
        return applicationService.readSpecificApplication(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateApplicationById(@PathVariable Long id, @RequestBody Map<String, Object> applicationMap) throws ApplicationNotFound, InvalidStatus {
        return applicationService.updateApplication(id, applicationMap);
    }

    @GetMapping("/app/userid/{id}")
    public ResponseEntity<List<Object>> getApplicationsByUser(@PathVariable Long id) throws ApplicationNotFound {
        return applicationService.getApplicationsByUser(id);
    }
}
