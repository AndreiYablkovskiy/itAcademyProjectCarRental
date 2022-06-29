package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.car_rental.model.entity.RepairPayment;
import project.car_rental.service.RepairPaymentService;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair_payment")
public class RepairPaymentController {
    private final RepairPaymentService repairPaymentService;

    @GetMapping("/new")
    String showNewRepairPaymentForm(@RequestParam("order") Integer orderId, Model model){
        RepairPayment repairPayment = repairPaymentService.createRepairPaymentForOrder(orderId);
        model.addAttribute("repairPayment", repairPayment);
        return "repair_payment/new";
    }

    @PostMapping("/new")
    public String saveRepairPaymentAndUpdateCarStatus (@RequestParam("order") Integer orderId, @RequestParam("carStatus") Integer carStatusId, @RequestParam("car") Integer carId
            ,@ModelAttribute("repair_payment") @Valid RepairPayment repairPayment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "repair_payment/new";
        }
        repairPaymentService.saveRepairPaymentAndUpdateCarStatus(carId, carStatusId, repairPayment);
        return "redirect:/admin/order?id="+ orderId;
    }

    @GetMapping("/details")
    public String showDetails (@RequestParam("id") Integer repairPaymentId, Model model){
        RepairPayment repairPayment = repairPaymentService.findById(repairPaymentId);
        model.addAttribute("repairPayment", repairPayment);
        return "repair_payment/details";
    }
}
