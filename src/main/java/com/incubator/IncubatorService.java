package com.incubator;

import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class IncubatorService {

    private final IncubatorRepository repository;

    public IncubatorService(IncubatorRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> createApplication(Incubator incubator) {
        repository.save(incubator);
        return new ResponseEntity<>(incubator, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> readApplications() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteApplication(Long id) throws IncubatorNotFound {
        Optional<Incubator> foundIncubator = repository.findById(id);

        if (foundIncubator.isEmpty()) {
            throw new IncubatorNotFound("Application does not exist, therefore not deleted");
        }

        repository.deleteById(id);
        return new ResponseEntity<>("Application deleted", HttpStatus.OK);

    }

    public ResponseEntity<Object> readSpecificApplication(Long id) throws IncubatorNotFound {
        Optional<Incubator> foundIncubator = repository.findById(id);

        if (foundIncubator.isEmpty()) {
            throw new IncubatorNotFound("Application does not exist");
        }

        return new ResponseEntity<>(foundIncubator.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateApplication(Long id, Map<String, Object> applicationMap) throws IncubatorNotFound, InvalidStatus {
        Optional<Incubator> checkForIncubator = repository.findById(id);

        if (checkForIncubator.isEmpty()) {
            throw new IncubatorNotFound("Application does not exist, therefore not updated");
        }

        Incubator foundIncubator = checkForIncubator.get();

        boolean isInvalidStatus = false;

        applicationMap.forEach((k, v) -> {
            switch (k) {
                case "fName":
                    foundIncubator.setfName((String) v);
                    break;
                case "lName":
                    foundIncubator.setlName((String) v);
                    break;
                case "mI":
                    foundIncubator.setmI((String) v);
                    break;
                case "dodId":
                    foundIncubator.setDodId((String) v);
                    break;
                case "rank":
                    foundIncubator.setRank((String) v);
                    break;
                case "dob":
                    foundIncubator.setDob(LocalDate.parse(v.toString()));
                    break;
                case "lastACFT":
                    foundIncubator.setLastACFT(LocalDate.parse(v.toString()));
                    break;
                case "acftScore":
                    foundIncubator.setAcftScore((Integer) v);
                    break;
                case "height":
                    foundIncubator.setHeight((Integer) v);
                    break;
                case "weight":
                    foundIncubator.setWeight((Integer) v);
                    break;
                case "techBG":
                    foundIncubator.setTechBG((String) v);
                    break;
                case "motivation":
                    foundIncubator.setMotivation((String) v);
                    break;
                case "referenceName":
                    foundIncubator.setReferenceName((String) v);
                    break;
                case "referenceRank":
                    foundIncubator.setReferenceRank((String) v);
                    break;
                case "referenceEmail":
                    foundIncubator.setReferenceEmail((String) v);
                    break;
                case "referencePhone":
                    foundIncubator.setReferencePhone((String) v);
                    break;
                case "status":
                    try {
                        foundIncubator.setStatus((String) v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "dateSubmitted":
                    foundIncubator.setDateSubmitted(LocalDate.parse(v.toString()));
                    break;
                default:
                    break;
            }
        });

        this.repository.save(foundIncubator);

        return new ResponseEntity<>(foundIncubator, HttpStatus.OK);
    }
}