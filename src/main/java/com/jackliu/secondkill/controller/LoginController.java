package com.jackliu.secondkill.controller;


import com.jackliu.secondkill.service.IUserService;
import com.jackliu.secondkill.service.impl.UserServiceImpl;
import com.jackliu.secondkill.vo.LoginVo;
import com.jackliu.secondkill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//@RestControlle基本上与@Controller注解相同，只不过添加了@ResponseBody注解，
// 表示方法返回值会直接作为HTTP响应正文返回。，而不是页面跳转
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        log.info(loginVo.getPassword());
        return userService.doLogin(loginVo, request, response);
    }

}
