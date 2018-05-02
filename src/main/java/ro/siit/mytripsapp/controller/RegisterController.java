package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.siit.mytripsapp.entity.user.User;
import ro.siit.mytripsapp.repository.UserRepository;

import javax.validation.Valid;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register/newUser")
    public User addUser(@Valid @RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }
}
