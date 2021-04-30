package com.example.bootstrap.controller;


import com.example.bootstrap.model.User;
import com.example.bootstrap.service.RoleService;
import com.example.bootstrap.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/user")
    public String getUserPage(Principal principal, Model model) {
        String name = principal.getName();
        User user = (User) userDetailsService.loadUserByUsername(name);
        model.addAttribute("user", user);
        return "user";
    }
}
