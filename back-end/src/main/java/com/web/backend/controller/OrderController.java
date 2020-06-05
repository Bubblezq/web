package com.web.backend.controller;

import com.web.backend.bean.User;
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
public class OrderController {
    private final JdbcTemplate jdbcTemplate;

    public OrderController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/myOrder",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> myOrder(User user){
        String userName=user.getUsername();
        System.out.println(userName);
        String sql="select * from product p join myorder o where p.productid=o.productid and o.username=:key";//SQL查询语句
        MapSqlParameterSource source=new MapSqlParameterSource();
        source.addValue("key",userName);
        NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(jdbcTemplate);
        List<Map<String,Object>> list=template.queryForList(sql,source);
        return list;
    }
}
