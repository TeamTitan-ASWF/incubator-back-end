package com.incubator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class IncubatorController {

    private final IncubatorService incubatorService;

    public IncubatorController(IncubatorService incubatorService) {
        this.incubatorService = incubatorService;
    }

    @PostMapping
    public ResponseEntity<Object> createsApplication (@RequestBody Incubator incubator) {
        return incubatorService.createApplication (incubator);
    }

    @GetMapping
    public ResponseEntity<Object> getAllApplications() {
        return incubatorService.readApplications();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplication(@PathVariable Long id) throws IncubatorNotFound {
        return incubatorService.deleteApplication(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readApplicationById(@PathVariable Long id) throws IncubatorNotFound {
        return incubatorService.readSpecificApplication(id);
    }

    @GetMapping("/dod/{dodId}")
    public ResponseEntity<Incubator> readApplicationByDodId(@PathVariable String dodId) throws IncubatorNotFound {
        return incubatorService.readApplicationByDodId(dodId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateApplicationById(@PathVariable Long id, @RequestBody Map<String, Object> applicationMap) throws IncubatorNotFound, InvalidStatus {
        return incubatorService.updateApplication(id, applicationMap);
    }



}
