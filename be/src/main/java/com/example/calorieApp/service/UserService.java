package com.example.calorieApp.service;

import com.example.calorieApp.model.User;
import com.example.calorieApp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        System.out.println(user.toString());
        System.out.println(user.getPassword());
        String encodedPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);
        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(userDetails.getUserName());
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
