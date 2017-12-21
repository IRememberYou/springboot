package com.wjp.repository;

import com.wjp.entity.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pinan on 2017/12/11.
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
