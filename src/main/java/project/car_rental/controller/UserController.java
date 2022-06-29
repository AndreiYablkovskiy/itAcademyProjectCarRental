package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.car_rental.model.entity.Order;
import project.car_rental.service.UserService;
import project.car_rental.model.entity.User;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String newUser (@ModelAttribute("user") User user) {
        return "users/registration";
    }

    @PostMapping("/registration")
    public String registration (@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "users/registration";
        }
        if (!userService.saveUserOrUpdate(user)){
            model.addAttribute("usernameAlreadyExist", "A user with the same name already exists");
            return "users/registration";
        }
        return "redirect:/";
    }

    @GetMapping("/account")
    public String account (Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Order> userOrders = userService.findOrdersByUserId(user.getId());
        model.addAttribute("orders", userOrders);
        return "users/account";
    }
    @GetMapping("/account/edit")
    public String editAccount (@RequestParam Integer id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }
    @PostMapping("/account/edit")
    public String editAccount (@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        if (!userService.saveUserOrUpdate(user)){
            model.addAttribute("usernameAlreadyExist", "A user with the same name already exists");
            return "users/edit";
        }
        return "redirect:/logout";
    }

    @GetMapping("/order")
    public String getUserOrder (@RequestParam Integer id, Model model){
        Order order = userService.findUserOrder(id);
        model.addAttribute("order", order);
        return "users/order";
    }

}
