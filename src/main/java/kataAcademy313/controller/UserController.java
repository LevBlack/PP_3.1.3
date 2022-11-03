package kataAcademy313.controller;


import kataAcademy313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String showCurrentUser(ModelMap model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }
}
