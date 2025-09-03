package com.oralcancer.oralcancer.Service;

import com.oralcancer.oralcancer.Entity.User;
import com.oralcancer.oralcancer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
   private  UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtilService jwtUtilService;
    @Override
    public boolean add(User user) {
        User x = userRepository.save(user);
        if(x==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id, User user) {
         boolean flage=false;
         try{
             userRepository.deleteById(id);
             flage=true;
        }catch (Exception e){
             e.printStackTrace();
         }
     return flage;
    }

    @Override
    public String verify(User user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if(authenticate.isAuthenticated())
            return jwtUtilService.generateToken(user);
        else
            return  "invalid credencial";
    }


}
