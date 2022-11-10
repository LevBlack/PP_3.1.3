package kataAcademy313.controller;

import kataAcademy313.models.Role;
import kataAcademy313.models.User;
import kataAcademy313.services.RoleService;
import kataAcademy313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public String allUsers(Model model, Principal principal) {

        User authUser = userService.getUserByUsername(principal.getName());
        model.addAttribute("authUser", authUser);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        User newUser = new User();
        model.addAttribute("newUser", newUser);

        Set<Role> roleSet = new HashSet<>(roleService.getSetRole());
        model.addAttribute("setRoles", roleSet);

        return "main";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id, int[] rolesID) {

        user.setRoles(roleService.roleSetById(rolesID));
        userService.update(id, user);

        return "redirect:/admin";
    }

    @PostMapping()
    public String create(@ModelAttribute("newUser") User user, int[] rolesID) {

        user.setRoles(roleService.roleSetById(rolesID));
        userService.addUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {

        userService.removeUser(id);

        return "redirect:/admin";
    }
}
