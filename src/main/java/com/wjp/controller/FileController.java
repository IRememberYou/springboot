package com.wjp.controller;

import com.wjp.base.Constan;
import com.wjp.entity.FileManger;
import com.wjp.repository.FileMangerRepostiory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * Created by pinan on 2017/12/18.
 */
@Controller
public class FileController {
    @Autowired
    FileMangerRepostiory fileMangerRepostiory;

    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("files") MultipartFile[] files) {
        if (files == null) {
            return "文件为空";
        }
        for (MultipartFile file : files) {
            //从数据库中查询是否存在
            FileManger serverFile = fileMangerRepostiory.findByFileurl(file.getOriginalFilename());
            if (serverFile != null) {
                return "shang chuan shi bai ,fu wu qi shang yi cun zai";
            }
            //保存文件路径在数据库中
            fileMangerRepostiory.save(new FileManger(file.getOriginalFilename()));
            saveFile(file);
        }
        return "upload success";
    }

    private boolean saveFile(MultipartFile file) {
        String filePath = Constan.LOCAL_FILE_URL + file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

//    /**
//     *
//     */
//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    @ResponseBody
//    public String download(@RequestParam("fileName") String fileName, HttpServletResponse res) throws IOException {
//        FileManger file = fileMangerRepostiory.findByFileurl(fileName);
//        if (file != null) {
//            String fileUrl = Constan.LOCAL_FILE_URL + fileName;
//            //定义输出类型
//            res.setContentType("application/octet-stream;charset=utf-8");
//            //兼容浏览器
//            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            //传递文件名
//            res.setHeader("filename", fileName);
//            FileUtils.copyFile(new File(fileUrl), res.getOutputStream());
//            return "xia zai cheng gong";
//        } else {
//            return "wen jian bu cun zai";
//        }
//    }


    /**
     * 文件下载
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public String downloadMedia(HttpServletResponse res, HttpServletRequest request) {
        OutputStream outputStream = null;
        try {
            //处理中文乱码
            request.setCharacterEncoding("UTF-8");
            //获取文件名
            String fileName = new String(request.getParameter("fileName").getBytes("iso8859-1"), "UTF-8");
            //判断数据库中是否存在
            if (fileMangerRepostiory.findByFileurl(fileName) == null) {
                return "文件不存在";
            }
            //处理浏览器兼容
            Enumeration enumeration = request.getHeaders("User-Agent");
            String browserName = (String) enumeration.nextElement();
            boolean isMSIE = browserName.contains("MSIE");
            if (isMSIE) {
                res.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF8"));
            } else {
                res.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            }
            //定义输出类型
            res.setContentType("application/octet-stream;charset=utf-8");
            res.setHeader("filename", fileName);
            //文件路径
            fileName = Constan.LOCAL_FILE_URL + File.separator + fileName;
            outputStream = res.getOutputStream();
            File file = new File(fileName);
            FileUtils.copyFile(file, outputStream);
        } catch (IOException e) {
            System.err.println(e);
            return "下载失败";
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
        return "文件下载成功";
    }

    @RequestMapping("gomoreup")
    public String goup() {
        return "my_more_upload";
    }
}
