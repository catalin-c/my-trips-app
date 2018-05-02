package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.entity.Trip;
import ro.siit.mytripsapp.entity.user.User;
import ro.siit.mytripsapp.model.TripModel;
import ro.siit.mytripsapp.model.UserModel;
import ro.siit.mytripsapp.repository.UserRepository;

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
}
