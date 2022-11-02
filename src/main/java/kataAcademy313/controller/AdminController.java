package kataAcademy313.controller;

import kataAcademy313.models.Role;
import kataAcademy313.models.User;
import kataAcademy313.services.RoleService;
import kataAcademy313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String allUsers(Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "main";
    }

    @GetMapping("/reg-user")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("newUser", user);
        Set<Role> roleSet =  roleService.getSetRole();
        model.addAttribute("setRoles", roleSet );

        return "reg-user";
    }

    @GetMapping("/update-user/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {

        User user = userService.getUser(id);
        model.addAttribute("editUser", user);
        Set<Role> roleSet =  roleService.getSetRole();
        model.addAttribute("setRoles", roleSet );
        System.out.println(user.getRoles());

        return "update-user";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("editUser") User user,@PathVariable("id") int id) {

        userService.update(id, user);

        return "redirect:/admin";
    }

    @PostMapping()
    public String create(@ModelAttribute("newUser") User user) {

        userService.addUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {

        userService.removeUser(id);

        return "redirect:/admin";
    }
}
