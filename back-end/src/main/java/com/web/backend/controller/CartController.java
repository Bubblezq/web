package com.web.backend.controller;

import com.web.backend.bean.Cart;
import com.web.backend.bean.User;
import com.web.backend.service.CartService;
import com.web.backend.service.UserService;
import com.web.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class CartController {
    private final JdbcTemplate jdbcTemplate;

    public CartController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> myCart(User user){
        String userName=user.getUsername();
        System.out.println(userName);
        String sql="select * from product p where p.productid in (select c.productid from cart c where c.username=:key)";//SQL查询语句
        MapSqlParameterSource source=new MapSqlParameterSource();
        source.addValue("key",userName);
        NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(jdbcTemplate);
        List<Map<String,Object>> list=template.queryForList(sql,source);
        return list;
    }


    @RequestMapping(value="/addCart",method = RequestMethod.POST)
    @ResponseBody
    public Result addCart(Cart cart){
        System.out.println(cart.getUsername());
        return cartService.addCart(cart);
    }

    @RequestMapping(value="/pay",method = RequestMethod.POST)
    @ResponseBody
    public Result pay(Cart cart){
        System.out.println(cart.getUsername());
        return cartService.pay(cart);
    }
}
