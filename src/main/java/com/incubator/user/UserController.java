package com.incubator.user;


import com.incubator.exceptions.UserNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping ("/user")
    public ResponseEntity<String> postsOneUser(@RequestBody User user) throws UserNotFound {
        return userService.postOneUser (user);
    }

//    @PostMapping ("/login")
//    public ResponseEntity<Object> authenticate(@RequestBody HashMap<String,String> userInfo) {
//        return userService.authenticateUser (userInfo);
//    }

//    @PostMapping ("/login")
//    public ResponseEntity<Object> authenticate(@RequestBody User user) {
//        return userService.authenticateUser (user);
//    }
//@PostMapping ("/login")
//    public ResponseEntity<Object> authenticate(@RequestBody String user) throws Exception {
//        return userService.authenticateUser (user);
//    }


    @RequestMapping(method = RequestMethod.POST, value = "/login", params = {"user"})
    public ResponseEntity<Object> authenticate(@RequestParam(value = "user") String user) throws Exception {
        return userService.authenticateUser(user);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login", params = {"userName", "password"})
    public ResponseEntity<Object> authenticate(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) throws Exception {
        return userService.authenticateUserFromForm (userName, password);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody Map<String, Object> userMap) throws UserNotFound {
        return userService.updateUser(id, userMap);
    }
}
