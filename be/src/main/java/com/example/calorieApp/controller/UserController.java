package com.example.calorieApp.controller;

import com.example.calorieApp.DTO.UserDTO;
import com.example.calorieApp.model.User;
import com.example.calorieApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return userService.findAllUsers().stream()
                .map(user -> new UserDTO(user.getId(), user.getUserName(), user.getEmail(), user.getPassword()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(new UserDTO(user.getId(), user.getUserName(), user.getEmail(), user.getPassword())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(new UserDTO(savedUser.getId(), savedUser.getUserName(),savedUser.getEmail(),savedUser.getPassword()));
    }

}
