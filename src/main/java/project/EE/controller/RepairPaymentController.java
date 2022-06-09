package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.EE.model.entity.Order;
import project.EE.model.entity.RepairPayment;
import project.EE.model.entity.User;
import project.EE.model.repository.RepairPaymentRepository;
import project.EE.service.OrderService;
import project.EE.service.RepairPaymentService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair_payment")
public class RepairPaymentController {
    private final RepairPaymentService repairPaymentService;
    private final OrderService orderService;

    @GetMapping("/create")
    String createRepairPayment(@RequestParam("order") Integer orderId, Model model){
        RepairPayment repairPayment = new RepairPayment();
        Order order = orderService.findById(orderId);
        repairPayment.setOrder(order);
        model.addAttribute("repairPayment", repairPayment);
        return "repair/payment";
    }

    @PostMapping("/create")
    public String registration (@ModelAttribute("repair_payment") @Valid RepairPayment repairPayment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "repair/payment";
        }
        repairPaymentService.save(repairPayment);
        return "redirect:/";
    }
}
