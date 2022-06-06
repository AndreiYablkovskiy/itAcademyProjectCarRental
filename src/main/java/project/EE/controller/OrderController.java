package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.EE.model.entity.Order;
import project.EE.model.repository.OrderRepository;
import project.EE.service.OrderService;
import project.EE.service.UserService;

import java.security.Principal;



@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderRepository orderRepository;


    @GetMapping("/new")
    public String newOrder (@RequestParam("id") Integer carId, Model model) {
        model.addAttribute("carId", carId);
        return "orders/new";
    }

    @PostMapping("/new")
    public String createOrder (@RequestParam Integer carId, @RequestParam() String rentalStart
            ,@RequestParam() String rentalEnd, Principal principal) {
        if (rentalStart == "" || rentalEnd == "") {
            return "redirect:/car?id=" + carId;
        }
        orderService.crateNewOrder(carId, rentalStart, rentalEnd, principal);
        return "redirect:/orders/created";
    }

    @GetMapping("/created")
    public String showCreatedOrder(Principal principal, Model model){
        Order order = orderService.showNewOrder(principal);
        model.addAttribute("order", order);
        return "orders/created";
    }

    @GetMapping("/payment")
    public String payTheOrder(@RequestParam Integer orderId){
        orderService.updateOrderStatusToPaid(orderId);
        return "orders/payment";
    }

}
