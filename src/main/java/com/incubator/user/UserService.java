package com.incubator.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<String> postOneUser(User user) {
        Optional<User> realUser = repository.findByUserName(user.getUserName());
        if (realUser.isPresent()) {
            return new ResponseEntity<>("User name already taken", HttpStatus.NOT_ACCEPTABLE);

        }
        repository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    public ResponseEntity<User> getsOneUser(Long id) {
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> authenticateUser(HashMap<String, String> userInfo) {
        Optional<User> realUser = repository.findByUserName(userInfo.get("userName"));
        if (realUser.isEmpty()) {
            return new ResponseEntity<>("User name invalid", HttpStatus.NOT_FOUND);
        } else {
            if (realUser.get().getPassword().equals(userInfo.get("password"))) {
                return new ResponseEntity<>(realUser.get(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Not Authenticated", HttpStatus.NOT_ACCEPTABLE);
            }
        }

    }

    public ResponseEntity<String> updateUser(Long id, Map<String, Object> userMap) throws UserNotFound {
        Optional<User> checkForUser = repository.findById(id);

        if (checkForUser.isEmpty()) {
            throw new UserNotFound("User does not exist, therefore not updated");
        }

        User foundUser = checkForUser.get();

        for (String k : userMap.keySet()) {
            switch (k) {
                case "fName":
                    foundUser.setfName((String) userMap.get(k));
                    break;
                case "lName":
                    foundUser.setlName((String) userMap.get(k));
                    break;
                case "mI":
                    foundUser.setmI((String) userMap.get(k));
                    break;
                case "dodId":
                    foundUser.setDodId((String) userMap.get(k));
                    break;
                case "rank":
                    foundUser.setRank((String) userMap.get(k));
                    break;
                case "dob":
                    foundUser.setDob(LocalDate.parse(userMap.get(k).toString()));
                    break;
                default:
                    break;
            }
        }

        this.repository.save(foundUser);
        return new ResponseEntity<>(String.format("User with id %d updated.", id), HttpStatus.OK);
    }
}



