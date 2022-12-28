package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "user-list";
    }

    @GetMapping("/update/{id}")
    public String getFormForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("rolesList", roleService.getAllRoles());
        return "user-update";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam String[] roles1) {
        List<Role> listroles = new ArrayList<>();
        for (String s : roles1) {
            listroles.add(roleService.getByName(s));
        }
        user.setRoles(listroles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/create")
    public String getFormForCreate(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("rolesList", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam String[] roles1) {
        List<Role> listRoles = new ArrayList<>();
        for (String s : roles1) {
            listRoles.add(roleService.getByName(s));
        }
        user.setRoles(listRoles);
        userService.addUser(user);
        return "redirect:/admin";
    }
}


