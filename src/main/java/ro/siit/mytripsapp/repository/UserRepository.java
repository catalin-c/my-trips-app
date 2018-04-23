package ro.siit.mytripsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.siit.mytripsapp.entity.User;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

}