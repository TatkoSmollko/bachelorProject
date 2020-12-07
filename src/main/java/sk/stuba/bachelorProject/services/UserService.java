package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String userName) {
        return userRepository.findById(userName).orElseThrow(() -> new ObjectNotFoundException(userName, "User"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String userName, User user) {
        User userToUpdate = userRepository.findById(userName).orElseThrow(() -> new ObjectNotFoundException(userName, "User"));
        userToUpdate.setAuthorities(user.getAuthorities());
        userToUpdate.setEnabled(user.getEnabled());
        return userRepository.save(userToUpdate);
    }

    public void deleteUser(String userName) {
        User userToUpdate = userRepository.findById(userName).orElseThrow(() -> new ObjectNotFoundException(userName, "User"));
    }
}
