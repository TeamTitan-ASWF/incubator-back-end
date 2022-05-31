package com.incubator.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getsOneUser(@PathVariable Long id) {
        return userService.getsOneUser(id);
    }

    @PostMapping ("/create_user")
    public ResponseEntity<String> postsOneUser(@RequestBody User user) {
        return userService.postOneUser (user);
    }

    @PostMapping ("/login")
    public ResponseEntity<String> authenticate(@RequestBody HashMap<String,String> userInfo) {
        return userService.authenticateUser (userInfo);
    }




}
