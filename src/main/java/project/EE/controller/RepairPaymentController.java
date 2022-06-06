package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.EE.model.repository.RepairPaymentRepository;

@Controller
@RequiredArgsConstructor
public class RepairPaymentController {
    private final RepairPaymentRepository repairPaymentRepository;
}
