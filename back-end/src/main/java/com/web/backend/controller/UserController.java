package com.web.backend.controller;

import com.web.backend.bean.User;
import com.web.backend.service.UserService;
import com.web.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private UserService userService;
    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @RequestMapping(value="/regist",method = RequestMethod.POST)
    @ResponseBody
    public Result regist(User user){
        return userService.register(user);
    }
    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(User user){
        System.out.println("Controller testing");
        System.out.println(user);
        System.out.println(user.getUsername());
        return userService.login(user);
    }
}
