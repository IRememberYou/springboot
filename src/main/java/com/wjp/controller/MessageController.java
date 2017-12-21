package com.wjp.controller;

import com.wjp.entity.Message;
import com.wjp.repository.MessageRepository;
import com.wjp.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by pinan on 2017/12/13.
 */
@Controller
public class MessageController {
    @Autowired
    private MessageRepository repostitory;

    @RequestMapping("/goMessage")
    public String goMessage(@RequestParam("pageNow") int pageNow, @RequestParam("pageSize") int pageSize, ModelMap map) {
        if (pageSize == 0) {
            pageSize = 2;
        }
        //分页查询
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNow, pageSize, sort);
        Page<Message> all = repostitory.findAll(pageable);
        List<Message> content = all.getContent();
        int[] pageNum = new int[all.getTotalPages()];
        for (int i = 0; i < all.getTotalPages(); i++) {
            pageNum[i] = i;
        }
        map.addAttribute("messages", content);
        map.addAttribute("pageNum", pageNum);
        return "message";
    }

    //分页加载数据
    @ResponseBody
    @RequestMapping("/page")
    public HttpResult page(@RequestParam("pageNow") int pageNow, @RequestParam("pageSize") int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNow, pageSize, sort);
        Page<Message> all = repostitory.findAll(pageable);
        return new HttpResult()
                .setData(null)
                .setCode(200)
                .setMessage("请求成功");
    }
}
