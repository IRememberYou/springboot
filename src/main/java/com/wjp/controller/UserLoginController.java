package com.wjp.controller;

import com.wjp.entity.UserForm;
import com.wjp.server.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pinan on 2017/12/12.
 */
@Controller
public class UserLoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String login(UserForm userForm, ModelMap map) {
        UserForm userForm1 = loginService.checkUser(userForm);
        if (userForm1 != null) {
            map.addAttribute("user", userForm);
            System.out.println("跳转到main中");
            return "main";
        } else {
            map.addAttribute("error", "登陆失败");
            System.out.println("登陆失败error");
            return "error";
        }
    }

    @RequestMapping("/regist")
    public String regist(UserForm userForm, ModelMap map) {
        UserForm userForm1 = loginService.save(userForm);
        if (userForm1 != null) {
            map.addAttribute("user", userForm);
            System.out.println("注册成功,即将跳到login中");
            return "login";
        } else {
            map.addAttribute("error", "注册失败");
            System.out.println("注册失败，即将跳到error页面");
            return "error";
        }
    }

    @Autowired
    JavaMailSender javaMailSender;

    //发送邮件部分
    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMail() {
        System.out.println("正在发送邮件");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wjp1000@yeah.net");
        message.setTo("1504522283@qq.com");
        message.setSubject("主题:简单的邮件");
        message.setText("测试数据");
        System.out.println("信息组装完毕");
        javaMailSender.send(message);
        System.out.println("发送结束");
        return "发送成功";
    }
}
