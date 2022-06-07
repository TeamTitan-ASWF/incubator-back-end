package com.incubator.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubator.exceptions.ApplicationNotFound;
import com.incubator.exceptions.InvalidStatus;
import com.incubator.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository repository;
    private final UserRepository userRepo;

    public ApplicationService(ApplicationRepository repository, UserRepository userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    public ResponseEntity<Object> createApplication(HashMap<String, Object> userMap) {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        // JE - may be some problems here with these lines
        userMap.put("user", userRepo.findById(Long.valueOf(String.valueOf(userMap.get("user")))));
        userMap.put("dob", LocalDate.parse(userMap.get("dob").toString()));

        Application application = mapper.convertValue(userMap, Application.class);
        repository.save(application);
        return new ResponseEntity<>(application, HttpStatus.CREATED);

    }

    public ResponseEntity<Object> createApplications(Iterable<Application> applicationList) {
        repository.saveAll(applicationList);
        return new ResponseEntity<>(applicationList, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> readApplications() {
        return new ResponseEntity<>(repository.getReviewerApplicationList(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteApplication(Long id) throws ApplicationNotFound {
        Optional<Application> foundApplication = repository.findById(id);

        if (foundApplication.isEmpty()) {
            throw new ApplicationNotFound("Application does not exist, therefore not deleted");
        }

        repository.deleteById(id);
        return new ResponseEntity<>("Application deleted", HttpStatus.OK);

    }

    public ResponseEntity<Object> readSpecificApplication(Long id) throws ApplicationNotFound {
        Optional<Application> foundApplication = repository.findById(id);

        if (foundApplication.isEmpty()) {
            throw new ApplicationNotFound("Application does not exist");
        }

        return new ResponseEntity<>(foundApplication.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateApplication(Long id, Map<String, Object> applicationMap) throws ApplicationNotFound, InvalidStatus {
        Optional<Application> checkForApplication = repository.findById(id);

        if (checkForApplication.isEmpty()) {
            throw new ApplicationNotFound("Application does not exist, therefore not updated");
        }

        Application foundApplication = checkForApplication.get();

        boolean isInvalidStatus = false;

        for (String k : applicationMap.keySet()) {
            switch (k) {
                case "fName":
                    foundApplication.setfName((String) applicationMap.get(k));
                    break;
                case "lName":
                    foundApplication.setlName((String) applicationMap.get(k));
                    break;
                case "mI":
                    foundApplication.setmI((String) applicationMap.get(k));
                    break;
                case "dodId":
                    foundApplication.setDodId((String) applicationMap.get(k));
                    break;
                case "rank":
                    foundApplication.setRank((String) applicationMap.get(k));
                    break;
                case "dob":
                    foundApplication.setDob(LocalDate.parse(applicationMap.get(k).toString()));
                    break;
                case "lastACFT":
                    foundApplication.setLastACFT(LocalDate.parse(applicationMap.get(k).toString()));
                    break;
                case "acftScore":
                    foundApplication.setAcftScore(Integer.valueOf(String.valueOf(applicationMap.get(k))));
                    break;
                case "height":
                    foundApplication.setHeight(String.valueOf(applicationMap.get(k)));
                    break;
                case "weight":
                    foundApplication.setWeight(String.valueOf(applicationMap.get(k)));
                    break;
                case "techBG":
                    foundApplication.setTechBG((String) applicationMap.get(k));
                    break;
                case "motivation":
                    foundApplication.setMotivation((String) applicationMap.get(k));
                    break;
                case "referenceName":
                    foundApplication.setReferenceName((String) applicationMap.get(k));
                    break;
                case "referenceRank":
                    foundApplication.setReferenceRank((String) applicationMap.get(k));
                    break;
                case "referenceEmail":
                    foundApplication.setReferenceEmail((String) applicationMap.get(k));
                    break;
                case "referencePhone":
                    foundApplication.setReferencePhone((String) applicationMap.get(k));
                    break;
                case "referenceName2":
                    foundApplication.setReferenceName2((String) applicationMap.get(k));
                    break;
                case "referenceRank2":
                    foundApplication.setReferenceRank2((String) applicationMap.get(k));
                    break;
                case "referenceEmail2":
                    foundApplication.setReferenceEmail2((String) applicationMap.get(k));
                    break;
                case "referencePhone2":
                    foundApplication.setReferencePhone2((String) applicationMap.get(k));
                    break;
                case "referenceName3":
                    foundApplication.setReferenceName3((String) applicationMap.get(k));
                    break;
                case "referenceRank3":
                    foundApplication.setReferenceRank3((String) applicationMap.get(k));
                    break;
                case "referenceEmail3":
                    foundApplication.setReferenceEmail3((String) applicationMap.get(k));
                    break;
                case "referencePhone3":
                    foundApplication.setReferencePhone3((String) applicationMap.get(k));
                    break;
                case "status":
                    foundApplication.setStatus((String) applicationMap.get(k));
                    break;
                case "dateSubmitted":
                    foundApplication.setDateSubmitted(LocalDate.parse(applicationMap.get(k).toString()));
                    break;
                default:
                    break;
            }
        }

        this.repository.save(foundApplication);
        return new ResponseEntity<>(foundApplication, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Application> readApplicationByDodId(String dodId) throws ApplicationNotFound {
        Optional<Application> foundApplication = repository.findFirstByDodIdEquals(dodId);

        if (foundApplication.isEmpty()) {
            throw new ApplicationNotFound("Application does not exist, please check that your DODID is correct.");
        }

        return new ResponseEntity<>(foundApplication.get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Object>> getApplicationsByUser(Long userId) throws ApplicationNotFound {
        List<Application> foundApplication = repository.findByUserIdEquals(userId);

        if (foundApplication.isEmpty()) {
            throw new ApplicationNotFound("No application found with user id of " + userId);
        }

        return new ResponseEntity (foundApplication, HttpStatus.OK);
    }
}
