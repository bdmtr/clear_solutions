package bdmtr.github.clearsolutionstesttask.controller;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String jsonRequest = "{ \"email\": \"user@gmail.com\", \"firstName\": \"John\", \"lastName\": \"Userman\", \"birthdate\": \"1998-01-17\" }";

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddUserNew() throws Exception {
        mockMvc.perform(post("/api/v1/users/")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("user@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Userman"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateSomeUserFields() throws Exception {
        String jsonRequest = "{ \"email\": \"updated_user@gmail.com\", \"firstName\": \"Updated\", \"lastName\": \"User\", \"birthdate\": \"1990-05-20\" }";
        mockMvc.perform(patch("/api/v1/users/{id}", 1L) // Replace 1L with the actual user ID
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("updated_user@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.birthdate").value("1990-05-20"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateAllUserFields() throws Exception {
        String jsonRequest = "{ \"email\": \"updated_user@gmail.com\", \"firstName\": \"Updated\", \"lastName\": \"User\", \"birthdate\": \"1990-05-20\" }";
        mockMvc.perform(put("/api/v1/users/{id}", 1L) // Replace 1L with the actual user ID
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("updated_user@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.birthdate").value("1990-05-20"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteUser() throws Exception {
        Long userIdToDelete = 1L;
        mockMvc.perform(delete("/api/v1/users/{id}", userIdToDelete))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetUsersByBirthDateBetweenIsOk() throws Exception {
        LocalDate fromDate = LocalDate.parse("1990-01-01");
        LocalDate toDate = LocalDate.parse("2000-12-31");

        mockMvc.perform(get("/api/v1/users/by-dates")
                        .param("fromDate", fromDate.toString())
                        .param("toDate", toDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    public void testGetUsersByBirthDateBetweenAreWrong() throws Exception {
        LocalDate fromDate = LocalDate.parse("1990-01-01");
        LocalDate toDate = LocalDate.parse("1400-12-31");

        mockMvc.perform(get("/api/v1/users/by-dates")
                        .param("fromDate", fromDate.toString())
                        .param("toDate", toDate.toString()))
                .andExpect(status().isPreconditionFailed());
    }
}
