package com.mtime.demo.controller;

import com.mtime.demo.service.FaceCheckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;


@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private FaceCheckService faceCheckService;


    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(
            @RequestParam(value = "imgData", required = false, defaultValue = "") String imgData) {


        faceCheckService.generateImage(imgData.substring(22));

        return "";
    }

    /**
     * user regitser
     * @param imgData
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestParam(value = "imgData", required = false, defaultValue = "") String imgData,
                           @RequestParam(value = "userName", required = false, defaultValue = "") String userName) {
        String image=faceCheckService.generateImage(imgData.substring(22));
        if(!StringUtils.isEmpty(image)){
            return faceCheckService.addUser(userName,image).toString();

        }else{
             return "failed";
        }

    }


//    @RequestMapping("/getUserInfo")
//    @ResponseBody
//    public User getUserInfo() {
//        User user = userService.getUserInfo();
//        if(user!=null){
//            System.out.println("user.getName():"+user.getName());
//            logger.info("user.getAge():"+user.getAge());
//        }
//        return user;
//    }
}
