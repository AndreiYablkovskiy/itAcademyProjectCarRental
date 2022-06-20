package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.EE.model.entity.Order;
import project.EE.model.entity.User;
import project.EE.service.UserService;

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
        if (!userService.saveUser(user)){
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
        if (!userService.updateUser(user)){
            model.addAttribute("usernameAlreadyExist", "A user with the same name already exists");
            return "users/edit";
        }
        return "redirect:/logout";
    }

    @GetMapping("/order")
    public String getUserOrder (@RequestParam Integer id, Model model){
        Order order = userService.findUsersOrder(id);
        model.addAttribute("order", order);
        return "users/order";
    }

}
