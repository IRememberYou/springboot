package com.wjp.repository;

import com.wjp.entity.FileManger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pinan on 2017/12/14.
 */
public interface FileMangerRepostiory extends JpaRepository<FileManger,Integer> {
    FileManger findByFileurl(String fileurl);
}
