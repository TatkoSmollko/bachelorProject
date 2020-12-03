package sk.stuba.bachelorProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        BCryptPasswordEncoder cryptor = new BCryptPasswordEncoder();
        user.setPassword(cryptor.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
