package ro.siit.mytripsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.siit.mytripsapp.entity.User;
import ro.siit.mytripsapp.repository.UserRepository;

import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {

        List<User> users = (List<User>) userRepository.findAll();

        return users;
    }

}