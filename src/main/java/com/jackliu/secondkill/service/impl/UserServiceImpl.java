package com.jackliu.secondkill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackliu.secondkill.exception.GlobalException;
import com.jackliu.secondkill.mapper.UserMapper;
import com.jackliu.secondkill.pojo.User;
import com.jackliu.secondkill.service.IUserService;
import com.jackliu.secondkill.utils.CookieUtil;
import com.jackliu.secondkill.utils.MD5Utils;
import com.jackliu.secondkill.utils.UUIDUtil;
import com.jackliu.secondkill.utils.ValidatorUtil;
import com.jackliu.secondkill.vo.LoginVo;
import com.jackliu.secondkill.vo.RespBean;
import com.jackliu.secondkill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2023-05-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        //参数校验
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
////            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//
//        //TODO 因为我懒测试的时候，把手机号码和密码长度校验去掉了，可以打开。页面和实体类我也注释了，记得打开
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }

        User user = userMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
            //return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        //判断密码是否正确
        if (!MD5Utils.fromPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
            //return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        //生成Cookie
        String ticket = UUIDUtil.uuid();
        //getAttribute 和 setAttribute 方法是 HttpSession 接口中定义的用于操作 Session 中属性值的方法
        //其中 getAttribute 方法用于获取 Session 中指定的属性值，而 setAttribute 方法则用于向 Session 中添加一个属性并设置初始值。
        //调用 request.getSession() 方法可以获取一个 HttpSession 实例，它代表了当前客户端与服务端之间的会话。
        // 如果这是一个新的会话，即该客户端在过去没有和服务端建立过任何会话（没有任何已存在的 HttpSession 对象），
        // 那么调用 getSession() 方法会在服务端自动创建一个新的 HttpSession 实例。
        // 否则，会返回与该客户端关联的已有的 HttpSession 实例。
        request.getSession().setAttribute(ticket, user);//将用户信息放入session
        CookieUtil.setCookie(request, response,"userTicket", ticket);
        return RespBean.success();
    }

}
