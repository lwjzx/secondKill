package com.jackliu.secondkill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jackliu.secondkill.pojo.User;
import com.jackliu.secondkill.vo.LoginVo;
import com.jackliu.secondkill.vo.RespBean;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2023-05-01
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
