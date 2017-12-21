package com.wjp.server.impl;

import com.wjp.entity.UserForm;
import com.wjp.repository.UserLoginRepository;
import com.wjp.server.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pinan on 2017/12/12.
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserLoginRepository userLoginRepository;


    @Override
    public UserForm checkUser(UserForm userForm) {
        return userLoginRepository.findByUsernameAndPassword(userForm.getUsername(), userForm.getPassword());
    }

    @Override
    public UserForm save(UserForm userForm) {
        return userLoginRepository.save(userForm);
    }
}
