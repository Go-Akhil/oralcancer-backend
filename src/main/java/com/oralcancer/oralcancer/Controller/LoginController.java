package com.oralcancer.oralcancer.Controller;

import com.oralcancer.oralcancer.Entity.User;
import com.oralcancer.oralcancer.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:5174")
public class LoginController {
    @Autowired
    UserServiceImpl userServiceimpl;
    @PostMapping
    public String verify(@RequestBody User user) {

        return  userServiceimpl.verify(user);
    }
}
