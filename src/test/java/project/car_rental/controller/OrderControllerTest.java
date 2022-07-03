package project.car_rental.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class OrderControllerTest {
    private static final String TEST_USER = "test";
    @Autowired
    private MockMvc mockMvc;


    @Test
    void newOrder() throws Exception {
        mockMvc.perform(get("/orders/new").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("id"))
                .andExpect(view().name("orders/new"));
    }

    @Test
    void createOrder() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return TEST_USER;
            }
        };
        String start = LocalDateTime.now().plusHours(10).toString();
        String end = LocalDateTime.now().plusHours(20).toString();

        mockMvc.perform(post("/orders/new").param("id", "1").principal(principal)
                        .param("rentalStart", start)
                        .param("rentalEnd", end))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders/created"));
    }

    @Test
    void createOrderWhenRentalStartOrRentalEndIsEmpty() throws Exception {
        String start = "";
        String end = "";

        mockMvc.perform(post("/orders/new").param("id", "1")
                        .param("rentalStart", start).param("rentalEnd", end))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("timeIsEmpty"))
                .andExpect(model().attributeExists("id"))
                .andExpect(view().name("orders/new"));
    }

    @Test
    void createOrderWhenWrongRentalDateOrTime() throws Exception {
        String start = LocalDateTime.now().plusHours(20).toString();
        String end = LocalDateTime.now().plusHours(10).toString();

        mockMvc.perform(post("/orders/new").param("id", "1")
                        .param("rentalStart", start)
                        .param("rentalEnd", end))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("wrongRentalDateOrTime"))
                .andExpect(model().attributeExists("id"))
                .andExpect(view().name("orders/new"));
    }

    @Test
    void payTheOrder() throws Exception {
        mockMvc.perform(get("/orders/payment").param("order", "1")
                        .param("status", "1")
                        .param("carStatus", "1")
                        .param("car", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders/payment"));
    }

    @Test
    void cancelOrder() throws Exception {
        mockMvc.perform(post("/orders/cancel").param("order", "1")
                        .param("status", "1")
                        .param("carStatus", "1")
                        .param("car", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/account"));
    }
}