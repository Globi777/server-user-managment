package com.jdabrowski.serverusermanagment.controller;

import com.jdabrowski.serverusermanagment.model.Role;
import com.jdabrowski.serverusermanagment.model.User;
import com.jdabrowski.serverusermanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> authorization(Principal principal){
        if(principal == null || principal.getName() == null){
        return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/api/user/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
