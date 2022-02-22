package com.controller;

import com.model.Role;
import com.model.User;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getUsers(Principal principal, Model model) {
        model.addAttribute("user", userService.getUser(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }



    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, @ModelAttribute("listRoles") String[] roles) {
        System.out.println(user);
        System.out.println(roles);
        Set<Role> userRoles = new HashSet<>();
        for (String role: roles) {
            userRoles.add(roleService.getRole("ROLE_" + role));
            if (role.equals("ADMIN")) {
                userRoles.add(roleService.getRole("ROLE_USER"));
            }
        }
        user.setRoles(userRoles);
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @PatchMapping("/{id}/patch")
    public String updateUser(@ModelAttribute("user") User user, @ModelAttribute(
            "listRoles") String[] roles) {
        Set<Role> userRoles = new HashSet<>();
        for (String role: roles) {
            userRoles.add(roleService.getRole("ROLE_" + role));
            if (role.equals("ADMIN")) {
                userRoles.add(roleService.getRole("ROLE_USER"));
            }
        }
        user.setRoles(userRoles);
        userService.addUser(user);
        return "redirect:/admin/";
    }

//    @GetMapping("/{id}/delete")
//    public String deleteUser(ModelMap modelMap, @PathVariable("id") long id) {
//        modelMap.addAttribute("user", userService.getUser(id));
//        return "delete";
//    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/";
    }

}

