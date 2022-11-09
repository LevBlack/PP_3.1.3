package kataAcademy313.controller;

import kataAcademy313.models.User;
import kataAcademy313.services.RoleService;
import kataAcademy313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping ()
    public String allUser(Model model, Principal principal){
        User authUser = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", authUser);
        return "user";
    }
}
