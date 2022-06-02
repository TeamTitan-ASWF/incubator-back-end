package com.incubator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubator.user.User;
import com.incubator.user.UserRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    public void createUserTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User testUser = new User("CaptainAmerica","Iloveamerica");
        MockHttpServletRequestBuilder requestBuilder = post("/user").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testUser));
        this.mvc.perform(requestBuilder).andExpect(content().string("User created"));
    }


    @Test
    @Rollback
    @Transactional
    public void getUserTest() throws  Exception{
        User testUser = new User("CaptainAmerica","Iloveamerica");
        userRepository.save(testUser);
        mvc.perform(get("/user/"+ testUser.getId())).andExpect(jsonPath("$.userName",is("CaptainAmerica")));

    }

    }

