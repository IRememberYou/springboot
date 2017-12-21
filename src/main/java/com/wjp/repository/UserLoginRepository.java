package com.wjp.repository;

import com.wjp.entity.UserForm;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pinan on 2017/12/12.
 */
public interface UserLoginRepository extends CrudRepository<UserForm, Integer> {
    UserForm findByUsernameAndPassword(String username, String password);
}
