package com.wjp.server;

import com.wjp.entity.UserForm;
import org.springframework.stereotype.Service;

/**
 * Created by pinan on 2017/12/12.
 */
@Service
public interface LoginService {
    UserForm checkUser(UserForm userForm);

    UserForm save(UserForm userForm);
}
