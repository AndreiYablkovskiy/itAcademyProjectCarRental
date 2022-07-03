package project.car_rental.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import project.car_rental.model.entity.User;
import project.car_rental.service.impl.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class UserControllerTest {
    private static final String USERNAME_FOR_TEST_FROM_DB = "test";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceImpl userService;

    @Test
    void getRegistration() throws Exception {
        mockMvc.perform(get("/users/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("users/registration"));
    }

    @Test
    void PostRegistrationWhenUsernameAlreadyExist() throws Exception {
        User user = userService.findByUsername(USERNAME_FOR_TEST_FROM_DB);

        mockMvc.perform(post("/users/registration")
                        .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("usernameAlreadyExist"))
                .andExpect(view().name("users/registration"));
    }

    @Test
    void PostRegistration() throws Exception {
        User user = userService.findByUsername(USERNAME_FOR_TEST_FROM_DB);
        user.setUsername("test2");

        mockMvc.perform(post("/users/registration")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        user.setUsername(USERNAME_FOR_TEST_FROM_DB);
    }

}