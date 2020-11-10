package com.jdabrowski.serverusermanagment.service;

import com.jdabrowski.serverusermanagment.model.User;
import com.jdabrowski.serverusermanagment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findUsers(List<Long> idList){
        return userRepository.findUserIdList(idList);
    }

    @Override
    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    @Override
    public  User findUserByIdAndUsername(User user){
        return userRepository.findByIdAndUsername(user.getId(), user.getPassword()).orElse(null);
    }
}
