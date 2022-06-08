package com.incubator.user;

import com.incubator.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://cors-everywhere-me.herokuapp.com/http://ec2-18-216-140-13.us-east-2.compute.amazonaws.com:8080/", "http://localhost:3000/"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getsOneUser(@PathVariable Long id) {
        return userService.getsOneUser(id);
    }

    @PostMapping ("/user")
    public ResponseEntity<String> postsOneUser(@RequestBody User user) throws UserNotFound {
        return userService.postOneUser (user);
    }

    @PostMapping ("/user/multiple")
    public ResponseEntity<Object> postsUsers(@RequestBody Iterable<User> userList) throws UserNotFound {
        return userService.postUsers(userList);
    }

    @PostMapping ("/login")
    public ResponseEntity<Object> authenticate(@RequestBody HashMap<String,String> userInfo) throws Exception {
        if(userInfo.containsKey("userName") && userInfo.containsKey("password")) {
            return userService.authenticateUserFromForm (userInfo.get("userName"), userInfo.get("password"));
        } else if(userInfo.containsKey("googleData")) {
            return userService.authenticateUser(userInfo.get("googleData"));
        } else {
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody Map<String, Object> userMap) throws UserNotFound {
        return userService.updateUser(id, userMap);
    }
}
