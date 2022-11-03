package kataAcademy313.services;

import kataAcademy313.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

        List<User> getAllUsers();

        void addUser(User user);

        User getUser(int id);

        void removeUser(int id);

        void update(int id, User user);
        User getUserByUsername(String username);
    }
