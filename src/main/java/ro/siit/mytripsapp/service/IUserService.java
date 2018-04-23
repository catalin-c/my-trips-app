package ro.siit.mytripsapp.service;

import ro.siit.mytripsapp.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
}
