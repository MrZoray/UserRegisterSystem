package com.zoray.UserRegister.service.Impl;

import com.zoray.UserRegister.model.User;
import com.zoray.UserRegister.repository.UserRepository;
import com.zoray.UserRegister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User login(String username, String password) {
        User user = userRepository.findByUserNameAndPassword(username, password);
        return user;
    }

}
