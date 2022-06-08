package com.incubator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubator.application.Application;
import com.incubator.application.ApplicationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationRepository repository;

    private List<Application> appsList;

    // save json Application test data into table before all tests
    @BeforeAll
    void setup() throws Exception {
        String jsonStr = getJSON("/applications.json");
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        appsList = mapper.readValue(jsonStr, new TypeReference<List<Application>>(){});
        repository.saveAll(appsList);
    }

    @Test
    @Transactional
    @Rollback
    public void createApplicationTest() throws Exception {
        this.mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "user": 10,
                                    "lastACFT": "2022-05-19",
                                    "acftScore": 477
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lastACFT", is("2022-05-19")))
                .andExpect(jsonPath("$.acftScore", is(477)));
    }

    @Test
    @Transactional
    @Rollback
    public void readAllApplicationsTest() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fName", is("Joe")))
                .andExpect(jsonPath("$[0].lName", is("Star")))
                .andExpect(jsonPath("$[0].mI", is("n")))
                .andExpect(jsonPath("$[0].rank", is("E-4")));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteApplicationsTest() throws Exception {
        Long id = appsList.get(0).getId();
        String path = "/" + id + "";

        this.mvc.perform(delete(path))
                .andExpect(status().isOk())
                .andExpect(content().string("Application deleted"));
        this.mvc.perform(delete("/123"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Application does not exist, therefore not deleted"));


    }


    @Test
    @Transactional
    @Rollback
    public void readSpecificApplicationsTest() throws Exception {
        Long id = appsList.get(0).getId();
        String path = "/" + id + "";

        this.mvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fName", is("Joe")))
                .andExpect(jsonPath("$.lName", is("Star")))
                .andExpect(jsonPath("$.mI", is("n")))
                .andExpect(jsonPath("$.dodId", is("1234567890")))
                .andExpect(jsonPath("$.rank", is("E-4")))
                .andExpect(jsonPath("$.dob", is("1980-09-10")))
                .andExpect(jsonPath("$.lastACFT", is("2022-05-19")))
                .andExpect(jsonPath("$.acftScore", is(478)));

        this.mvc.perform(get("/14"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Application does not exist"));

    }

    @Test
    @Transactional
    @Rollback
    public void updateApplicationsTest() throws Exception {
        Long id = appsList.get(0).getId();
        String path = "/" + id + "";

        this.mvc.perform(patch(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "lastACFT":"2022-05-10",
                                    "weight": 160
                                }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fName", is("Joseph")))
                .andExpect(jsonPath("$.lName", is("Star")))
                .andExpect(jsonPath("$.mI", is("n")))
                .andExpect(jsonPath("$.dodId", is("1234567890")))
                .andExpect(jsonPath("$.rank", is("E-4")))
                .andExpect(jsonPath("$.dob", is("1980-09-10")))
                .andExpect(jsonPath("$.lastACFT", is("2022-05-10")))
                .andExpect(jsonPath("$.acftScore", is(478)))
                .andExpect(jsonPath("$.weight", is(160.0)));

        this.mvc.perform(patch("/14")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Application does not exist, therefore not updated"));
    }

    @Test
    @Transactional
    @Rollback
    public void updateWithInvalidStatusGivesError() throws Exception {
        Long id = appsList.get(0).getId();
        String path = "/" + id + "";

        this.mvc.perform(patch(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "status":"Joseph"
                                }
                                """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Status submitted, needs to be: pending, approved, denied, or rescinded and you used: Joseph"));
    }

    // helper method to read files
    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
