package com.wjp.controller;

import com.wjp.bean.BookBean;
import com.wjp.entity.FileManger;
import com.wjp.entity.Student;
import com.wjp.repository.FileMangerRepostiory;
import com.wjp.server.StudentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    FileMangerRepostiory fileMangerRepostiory;

//    @RequestMapping(method = RequestMethod.POST, path = "/upload")
//    @ResponseBody
//    public String upload(@RequestPart("file") MultipartFile file) {
//        try {
//            System.out.println("上传文件地带");
//            String fileUrl = "/Users/pinan/Downloads/project/springboot/src/file/" + file.getOriginalFilename();
//            fileMangerRepostiory.save(new FileManger(fileUrl));
//            FileUtils.writeByteArrayToFile(new File(fileUrl), file.getBytes());
//            return "upload success";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "upload error+\n" + e.toString();
//        }
//    }


    @RequestMapping("/gouploadui")
    public String gouploadui(ModelMap map) {
        List<FileManger> all = fileMangerRepostiory.findAll();
        map.addAttribute("files", all);
        return "upload";
    }

//    @RequestMapping(value = "/testDownload")
//    @ResponseBody
//    public String testDownload(@RequestParam("fileName") String fileName, HttpServletResponse res) {
//        res.setHeader("content-type", "application/octet-stream");
//        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        res.setHeader("filename", fileName);
//        try {
//            String fileUrl = "/Users/pinan/Downloads/project/springboot/src/file/" + fileName;
//            FileUtils.copyFile(new File(fileUrl), res.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "download error";
//        }
//        return "download success";
//    }


//    @RequestMapping(value = "/img")
//    public void img(HttpServletResponse res) {
//        String fileName = "1513580137223..jpg";
//
//        res.setHeader("content-type", "application/octet-stream");
//        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        res.setHeader("filename", fileName);
//        try {
//            String fileUrl = "/Users/pinan/Downloads/project/springboot/src/file/" + fileName;
//            FileUtils.copyFile(new File(fileUrl), res.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
