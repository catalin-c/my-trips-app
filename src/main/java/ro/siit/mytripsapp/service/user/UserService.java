package ro.siit.mytripsapp.service.user;

import ro.siit.mytripsapp.entity.user.User;
import ro.siit.mytripsapp.entity.user.UserCreateForm;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Iterable<User> getAllUsers();

    User create(UserCreateForm form);

}