package com.wjp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by pinan on 2017/12/14.
 */
@Entity
public class FileManger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fileurl;

    public FileManger() {
    }

    public FileManger(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
