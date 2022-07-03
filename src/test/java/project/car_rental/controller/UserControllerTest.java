package project.car_rental.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    public static final String NEW_USERNAME_FOR_TEST = "test2";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceImpl userService;

    @AfterEach
     void afterTestsSetUsername(){
        User user = userService.findByUsername(NEW_USERNAME_FOR_TEST);
        if(user != null) {
            user.setUsername(USERNAME_FOR_TEST_FROM_DB);
            userService.saveUserOrUpdate(user);
        }
    }

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
        user.setUsername(NEW_USERNAME_FOR_TEST);

        mockMvc.perform(post("/users/registration")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}