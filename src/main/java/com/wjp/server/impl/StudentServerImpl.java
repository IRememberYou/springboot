package com.wjp.server.impl;

import com.wjp.entity.Student;
import com.wjp.repository.StudentRepository;
import com.wjp.server.StudentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pinan on 2017/12/11.
 */
@Component
public class StudentServerImpl implements StudentServer {
    @Autowired
    StudentRepository studentRepository;

    public Student save(int age, String name) {
        Student stu = new Student();
        stu.setAge(age);
        stu.setName(name);
        return studentRepository.save(stu);
    }

    public void delete(int id) {
        studentRepository.delete(id);
    }

    public Student save(int id) {
        Student stu = new Student();
        stu.setId(id);
        stu.setAge(10);
        stu.setName("zhangsan");
        return studentRepository.save(stu);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }
}
