package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.EE.model.entity.Car;
import project.EE.model.entity.Order;
import project.EE.service.OrderService;
import project.EE.service.UserService;
import project.EE.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
}

//    @GetMapping("/{userId}")
//    public String  gtUser(@PathVariable("userId") Integer userId, Model model) {
//        model.addAttribute("allUsers", userServiceImpl.findUserById(userId));
//        return "userbyid";
//    }


