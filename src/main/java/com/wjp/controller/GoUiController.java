package com.wjp.controller;

import com.wjp.entity.FileManger;
import com.wjp.repository.FileMangerRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by pinan on 2017/12/21.
 */
@Controller
public class GoUiController {

    @Autowired
    FileMangerRepostiory fileMangerRepostiory;

    //下载页面
    @RequestMapping("/gouploadui")
    public String gouploadui(ModelMap map) {
        List<FileManger> all = fileMangerRepostiory.findAll();
        map.addAttribute("files", all);
        return "upload";
    }

    //主页
    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    //注册页面
    @RequestMapping("goregistui")
    public String goregistui() {
        return "regist";
    }
}
