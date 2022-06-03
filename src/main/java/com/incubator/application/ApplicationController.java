package com.incubator.application;

import com.incubator.exceptions.ApplicationNotFound;
import com.incubator.exceptions.InvalidStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Object> createsApplication (@RequestBody Application application) {
        return applicationService.createApplication (application);
    }

    @PostMapping("/multiple")
    public ResponseEntity<Object> createsApplications (@RequestBody Iterable<Application> incubatorList) {
        return applicationService.createApplications(incubatorList);
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

    @GetMapping("/dod/{dodId}")
    public ResponseEntity<Application> readApplicationByDodId(@PathVariable String dodId) throws ApplicationNotFound {
        return applicationService.readApplicationByDodId(dodId);
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
