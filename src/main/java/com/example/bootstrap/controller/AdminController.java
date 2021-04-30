package com.example.bootstrap.controller;


import com.example.bootstrap.model.Role;
import com.example.bootstrap.model.User;
import com.example.bootstrap.service.RoleService;
import com.example.bootstrap.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;

import java.util.Set;

//1. добавить отображение ролей на фронте
//        2. добавить возможность выбора роли при добавлении/изменении пользователя
//        3. добавить шифрование паролей
//        4. при добавлении нового пользователя юзер должен связываться с ролью
//        5. переименовать AppConfig чтобы оно отображало суть класса
//        6. перенести UserDetailsService в пакет security
//        7. поправить @GetMapping(value = {"/users", "/"})
//8. исправить удаление пользовател через post запрос
//        9. реализовать FetchType.LAZY


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;

    public AdminController(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String allUsers(Model model, Principal principal) {
        String name = principal.getName();
        User user = (User) userDetailsService.loadUserByUsername(name);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.listUser());
        return "admin";
    }

    @GetMapping(value = "/add")
    public String addUserPage(Model model, User user) {
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "roles") String[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for (String role : roles) {
            rolesSet.add(userService.getRoleByName(role));
        }
        user.setRoles(rolesSet);
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin";
    }

    @PostMapping(value = "/{id}/edit")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                           @RequestParam(value = "roles") String[] role) {

        Set<Role> rolesSet = new HashSet<>();
        for (String roles : role) {
            rolesSet.add(userService.getRoleByName(roles));
        }

        User userById = userService.getUserById(id);
        if (user.getPassword().isEmpty()) {
            user.setPassword(userById.getPassword());
        } else if (!user.getPassword().equals(userById.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        user.setRoles(rolesSet);
        userService.editUser(user);
        return "redirect:/admin/";
    }

    @PostMapping(value = {"/{id}"})
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/";
    }

}
