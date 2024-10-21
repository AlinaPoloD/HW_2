package com.example.demo.controllers;



import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.event.MouseEvent;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users =userService.findAll();
        model.addAttribute("users",users);
        return "user-list";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user) {
    return "user-create";

    }
    @PostMapping("/user-create")
    public String createUser(User user){
    userService.saveUser(user);
    return "redirect:/users";
    }

    @PostMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable int id){
    userService.deleteUser(id);
    return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateForm(@PathVariable Integer id , Model model) {
    User user = userService.getUserbyId(id);
    model.addAttribute("user",user);
    return "user-update";

    }
    @PostMapping("user-update")
    public String updateUser(@ModelAttribute("user") User user) {
    userService.updateUser(user);
    return "redirect:/users";

    }


}
