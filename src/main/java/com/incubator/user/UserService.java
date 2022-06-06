package com.incubator.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubator.exceptions.UserNotFound;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// tslint:disable-next-line
import io.jsonwebtoken.SignatureAlgorithm;



import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//import SignatureAlgorithm
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.util.*;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.SignatureAlgorithm.RS256;








@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<String> postOneUser(User user) throws UserNotFound {
        Optional<User> realUser = repository.findByUserName(user.getUserName());
        if (realUser.isPresent()) {
           // throw new UserNotFound("Uh");
            return new ResponseEntity<>("User name already taken", HttpStatus.NOT_ACCEPTABLE);

        }
        repository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    public ResponseEntity<User> getsOneUser(Long id) {
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> authenticateUserFromForm(String userName, String password) {
        Optional<User> realUser = repository.findByUserName(userName);
        if (realUser.isEmpty()) {
            return new ResponseEntity<>("User name invalid", HttpStatus.NOT_FOUND);
        } else {
            if (realUser.get().getPassword().equals(password)) {
                return new ResponseEntity<>(realUser.get(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Not Authenticated", HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    public ResponseEntity<Object> authenticateUser(String userToken) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String[] chunks = userToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        JsonNode payloadMap = mapper.readTree(payload);


        String email = payloadMap.get("email").asText();
        String fName = payloadMap.get("given_name").asText();
        String lName = payloadMap.get("family_name").asText();




        Optional<User> realUser = repository.findByUserName(email);
        if (realUser.isEmpty()) {
            User user = new User(email,fName,lName );
            return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(realUser.get(), HttpStatus.ACCEPTED);
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



