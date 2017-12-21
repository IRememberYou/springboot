package com.wjp.repository;

import com.wjp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pinan on 2017/12/13.
 */
public interface MessageRepository extends JpaRepository<Message,Integer> {
}
