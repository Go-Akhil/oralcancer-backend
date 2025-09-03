package com.oralcancer.oralcancer.Controller;

import com.oralcancer.oralcancer.Entity.User;
import com.oralcancer.oralcancer.Service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Registration")
@CrossOrigin(origins = "http://localhost:5174/")
public class ResisterController {
   @Autowired
   UserServiceImpl userServiceimpl;
   @Autowired
   PasswordEncoder passwordEncoder;
   @PostMapping
    public boolean add(@RequestBody  User user) {
        user.setRole(user.getRole().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServiceimpl.add(user);
    }



}
