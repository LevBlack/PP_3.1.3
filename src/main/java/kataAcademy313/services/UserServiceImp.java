package kataAcademy313.services;

import kataAcademy313.models.User;
import kataAcademy313.repositories.UserRepositories;
import kataAcademy313.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepositories userRepositories;

    @Autowired
    public UserServiceImp(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepositories.save(user);
    }

    @Override
    public User getUser(int id) {
        Optional<User> user = userRepositories.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userRepositories.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User userToUpdate = userRepositories.findById(id).orElseThrow( () -> new RuntimeException("User not found!"));

        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setRoles(user.getRoles());

        userRepositories.save(userToUpdate);
    }
    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositories.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }
}
