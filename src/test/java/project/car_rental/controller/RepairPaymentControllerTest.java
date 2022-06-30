package project.car_rental.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import project.car_rental.model.entity.RepairPayment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RepairPaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void showNewRepairPaymentForm() throws Exception {
        mockMvc.perform(get("/repair_payment/new").param("order", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("repairPayment"))
                .andExpect(view().name("repair_payment/new"));
    }

    @Test
    void saveRepairPaymentAndUpdateCarStatus() throws Exception {
        RepairPayment repairPayment = new RepairPayment();
        repairPayment.setId(1);
        repairPayment.setValue(100.0);
        repairPayment.setDescription("test description");

        mockMvc.perform(post("/repair_payment/new").param("order", "1")
                        .param("carStatus", "2").param("car", "1")
                        .flashAttr("repair_payment", repairPayment))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/order?id=1"));
    }

    @Test
    void showDetails() throws Exception {
        mockMvc.perform(get("/repair_payment/details").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("repairPayment"))
                .andExpect(view().name("repair_payment/details"));
    }
}