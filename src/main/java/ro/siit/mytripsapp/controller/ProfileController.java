package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.entity.user.User;
import ro.siit.mytripsapp.model.UserModel;
import ro.siit.mytripsapp.repository.UserRepository;

import javax.validation.Valid;

@RestController
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    private static UserModel mapToModel(User entity) {
        UserModel to = new UserModel();
        to.setId(entity.getId());
        to.setEmail(entity.getEmail());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());
        to.setCity(entity.getCity());
        to.setAddress(entity.getAddress());
        to.setPhone(entity.getPhone());
        return to;
    }

    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    public @ResponseBody UserModel getUser(@PathVariable(value = "id") Long id)
    {
        return mapToModel(userRepository.findUserById(id));
    }




    @PatchMapping("/updateProfile")
    public User updateProfile(@RequestParam Long id,
                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findUserById(id);

        if(userDetails.getFirstName() != null) {
            user.setFirstName(userDetails.getFirstName());
        }

        if(userDetails.getLastName() != null) {
            user.setLastName(userDetails.getLastName());
        }

        if(userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }

        if(userDetails.getCity() != null) {
            user.setCity(userDetails.getCity());
        }

        if(userDetails.getAddress() != null) {
            user.setAddress(userDetails.getAddress());
        }

        if(userDetails.getPhone() != null) {
            user.setPhone(userDetails.getPhone());
        }

        if(userDetails.getPasswordHash() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(userDetails.getPasswordHash());
            user.setPasswordHash(hashedPassword);
        }


        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
}
