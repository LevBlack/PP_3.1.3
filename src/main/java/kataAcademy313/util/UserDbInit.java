package kataAcademy313.util;

import kataAcademy313.models.Role;
import kataAcademy313.models.User;
import kataAcademy313.services.RoleService;
import kataAcademy313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDBInit {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserDBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void  createUsers() {

        Role role1 = new Role(1, "ROLE_ADMIN");
        Role role2 = new Role(2, "ROLE_USER");
        Role role3 = new Role(3, "");

        roleService.addRole(role1);
        roleService.addRole(role2);
        roleService.addRole(role3);

        Set<Role> set1 = new HashSet<>(1);
        set1.add(role1);
        Set<Role> set2 = new HashSet<>(1);
        set2.add(role2);
        Set<Role> set3 = new HashSet<>(1);
        set2.add(role3);

        User user1 = new User(1, "admin", "admin", 999,  "admin@email.com",  set1 );
        User user2 = new User(2, "test", "test", 20,  "test@email.com", set2 );
        User user3 = new User(2, "user", "user", 20,  "test@email.com", set3 );

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
    }
}