package com.wjp.controller;

import com.wjp.bean.BookBean;
import com.wjp.entity.Student;
import com.wjp.server.StudentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pinan on 2017/12/11.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    //获取配置的内容1
    @Value("${book.author}")
    String author;

    @RequestMapping("/p1")
    @ResponseBody
    public String test() {
        return "测试获取配置文件的内容，获取的内容如下：\n" + author;
    }

    //获取配置的内容2
    @Autowired
    BookBean bookBean;

    @RequestMapping("/p2")
    @ResponseBody
    public String test2() {
        return "测试获取配置文件的内容，获取的内容如下：\n" +
                bookBean.getAuthor() + "\n" +
                bookBean.getName() + "\n" +
                bookBean.getPinyin() + "\n";
    }

    /*------------------------------------华丽的分割线-----------------------------------------------------------*/
    //简单的实现增删改查
    @Autowired
    StudentServer studentServer;

    @RequestMapping("/save")
    @ResponseBody
    public Student save(@RequestParam("age") int age, @RequestParam("name") String name) {
        return studentServer.save(age, name);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") int id) {
        studentServer.delete(id);
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public Student update(@PathVariable("id") int id) {
        return studentServer.save(id);
    }

    @RequestMapping("/query")
    @ResponseBody
    public Iterable<Student> queryAll() {
        return studentServer.findAll();
    }
}
