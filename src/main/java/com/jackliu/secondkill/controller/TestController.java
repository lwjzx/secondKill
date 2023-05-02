package com.jackliu.secondkill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/demo")
public class TestController {

    /**
     * 测试页面跳转
     * @param model
     * @return hello.html
     */
    @RequestMapping("/test")
    public String hello(Model model){
        model.addAttribute("name", "jackLiu");
        return "hello";
    }
}
