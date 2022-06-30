package project.car_rental.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllOrders() throws Exception {
        mockMvc.perform(get("/admin/orders").param("statusId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attributeExists("statuses"))
                .andExpect(view().name("admin/orders"));
    }

    @Test
    void getOrderByIdWhenParamIdIsNull() throws Exception {
        mockMvc.perform(get("/admin/order"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("EmptyNumber"))
                .andExpect(view().name("admin/orders"));
    }

    @Test
    void getOrderByIdWhenParamIdNotFound() throws Exception {
        mockMvc.perform(get("/admin/order").param("id", "0"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orderNotFound"))
                .andExpect(view().name("admin/orders"));
    }

    @Test
    void getOrderById() throws Exception {
        mockMvc.perform(get("/admin/order").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("admin/order"));
    }

    @Test
    void getUserAccount() throws Exception {
        mockMvc.perform(get("/admin/user").param("username", "user12"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(view().name("admin/user"));
    }
}