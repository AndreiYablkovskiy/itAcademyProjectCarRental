package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.EE.service.impl.UserServiceImpl;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;

//    @GetMapping("/users")
//    public String userList(Model model) {
//        model.addAttribute("allUsers", userServiceImpl.findByUsername());
//        return "users";
//    }

//    @GetMapping("/{userId}")
//    public String  gtUser(@PathVariable("userId") Integer userId, Model model) {
//        model.addAttribute("allUsers", userServiceImpl.findUserById(userId));
//        return "userbyid";
//    }

    @GetMapping("/")
    public String admin (){
        return "admin/admin";
    }
}
