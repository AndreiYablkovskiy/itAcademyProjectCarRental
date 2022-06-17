package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.EE.model.entity.Order;
import project.EE.model.entity.OrderStatus;
import project.EE.model.entity.User;
import project.EE.service.CarService;
import project.EE.service.OrderService;
import project.EE.service.OrderStatusService;
import project.EE.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final OrderStatusService orderStatusService;
    private final OrderService orderService;
    private final UserService userService;
    private final CarService carService;

    @GetMapping("/orders")
    public String getAllOrders(@RequestParam(required = false) Integer statusId, Model model) {
        List<Order> orders;
            if (statusId.equals(0)) {
                orders = orderService.getAllOrders();
            } else {
                orders = orderService.getByStatusId(statusId);
            }
        model.addAttribute("orders", orders);
        List<OrderStatus> orderStatuses = orderStatusService.findAll();
        model.addAttribute("statuses", orderStatuses);
        return "admin/orders";
    }

    @GetMapping("/order")
    public String getOrderById (@RequestParam(required = false) Integer id, Model model){
        if(id == null){
            model.addAttribute("EmptyNumber","The number should not be empty");
            return "admin/orders";
        }
        Optional<Order> order = orderService.findById(id);
        if(order.isEmpty()){
            model.addAttribute("orderNotFound", "Order not found");
            return "admin/orders";
        }
        model.addAttribute("order", order.get());
        return "admin/order";
    }

    @PostMapping("/order")
    public String updateOrderAndCarStatus (@RequestParam("order") Integer orderId, @RequestParam("status") Integer statusId
            ,@RequestParam(value = "carStatus", required = false) Integer carStatusId, @RequestParam(value = "car", required = false) Integer carId, Principal principal){
        String employeeName = principal.getName();
        orderService.updateOrderStatus(orderId, statusId, employeeName);
        if (carStatusId != null && carId != null) {
            carService.updateCarStatus(carId, carStatusId);
        }
        return "redirect:/admin/order?id="+ orderId;
    }

    @PostMapping("/order/info")
    public String updateOrderInfo (@RequestParam("order") Integer orderId, @RequestParam("info") String orderInfo, Principal principal){
        User employee = userService.findByUsername(principal.getName());
        orderService.updateOrderInfo(orderId, orderInfo, employee);
        return "redirect:/admin/order?id="+ orderId;
    }

    @GetMapping("/user")
    public String account (@RequestParam String username, Model model){
        User user = userService.findByUsername(username);
        List<Order> userOrders = userService.findOrdersByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", userOrders);
        return "admin/user";
    }

}



