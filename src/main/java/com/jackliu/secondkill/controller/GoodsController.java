package com.jackliu.secondkill.controller;


import com.jackliu.secondkill.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 跳转到商品页表界面
     * @param session
     * @param model Model 是一种用于在 Spring MVC 中传递数据的通用概念。它是一个从控制器发送到视图的对象容器，
     *              包含要呈现到用户的所有信息，并将其提供给受Spring MVC所管理的视图。Model 本质上就是一个 Java Map 对象
     * @param ticket
     * @return
     */
    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket){
        //会话失效，从新登陆
        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
        //从session中获取用户信息，用户信息已经被添加到session中
        User user = (User) session.getAttribute(ticket);
        //用户信息为空，则重新登录
        if(user==null){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
