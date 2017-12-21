package com.wjp.server;

import com.wjp.entity.Student;
import org.springframework.stereotype.Service;

/**
 * Created by pinan on 2017/12/11.
 */
@Service
public interface StudentServer {

    Student save(int age, String name);

    void delete(int id);

    Student save(int id);

    Iterable<Student> findAll();
}
