package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.car_rental.model.entity.Order;
import project.car_rental.service.OrderService;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/new")
    public String getNewOrder (@RequestParam("id") Integer carId, Model model) {
        model.addAttribute("id", carId);
        return "orders/new";
    }

    @PostMapping("/new")
    public String createOrder (@RequestParam("id") Integer carId, @RequestParam() String rentalStart
            ,@RequestParam() String rentalEnd, Principal principal, Model model) {
        if (rentalStart.isEmpty() || rentalEnd.isEmpty()) {
            model.addAttribute("timeIsEmpty", "Time should not be empty");
            model.addAttribute("id", carId);
            return "orders/new";
        }
        if(orderService.createNewOrder(carId, rentalStart, rentalEnd, principal)){
            return "redirect:/orders/created";
        }
        model.addAttribute("wrongRentalDateOrTime", "Wrong rental date or time");
        model.addAttribute("id", carId);
        return "orders/new";
    }

    @GetMapping("/created")
    public String getCreatedOrder(Principal principal, Model model){
        Order order = orderService.showNewOrder(principal);
        model.addAttribute("order", order);
        return "orders/created";
    }

    @GetMapping("/payment")
    public String payTheOrder(@RequestParam("order") Integer orderId, @RequestParam("status") Integer statusId
            ,@RequestParam("carStatus") Integer carStatusId, @RequestParam("car") Integer carId){
        orderService.updateOrderAndCarStatuses(carId, carStatusId, orderId, statusId);
        return "orders/payment";
    }

    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam("order") Integer orderId, @RequestParam("status") Integer statusId
            ,@RequestParam(value = "carStatus") Integer carStatusId, @RequestParam(value = "car") Integer carId){
        orderService.updateOrderAndCarStatuses(carId, carStatusId, orderId, statusId);
        return "redirect:/users/account";
    }
}
