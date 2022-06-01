package com.incubator.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

}



