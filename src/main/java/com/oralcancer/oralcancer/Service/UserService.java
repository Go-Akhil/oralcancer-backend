package com.oralcancer.oralcancer.Service;

import com.oralcancer.oralcancer.Entity.User;

public interface UserService {

    boolean add(User user);
    boolean delete(int id,User user);
    String verify(User user);

}
