package com.oralcancer.oralcancer.Controller;

import com.oralcancer.oralcancer.Entity.User;
import com.oralcancer.oralcancer.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Detection/Admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
    @Autowired
    UserServiceImpl userServiceimpl;

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable int id, @RequestBody User user) {
        return userServiceimpl.delete(id,user);
    }


}
