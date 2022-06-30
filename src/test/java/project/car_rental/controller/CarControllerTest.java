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
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllMarks() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("marks"))
                .andExpect(view().name("index"));
    }

    @Test
    void getCarsByMarkAndStatus() throws Exception {
        mockMvc.perform(get("/cars").param("statusId", "1").param("mark", "all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attributeExists("statusId"))
                .andExpect(model().attributeExists("statuses"))
                .andExpect(model().attributeExists("mark"))
                .andExpect(view().name("cars/cars"));
    }

    @Test
    void getCarById() throws Exception {
        mockMvc.perform(get("/car").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("car"))
                .andExpect(view().name("cars/carbyid"));
    }

    @Test
    void updateCarStatus() throws Exception {
        mockMvc.perform(post("/car").param("car", "2").param("carStatus", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car?id=2"));
    }
}