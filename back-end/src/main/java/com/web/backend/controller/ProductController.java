package com.web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class ProductController {
    private final JdbcTemplate jdbcTemplate;

    public ProductController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/product",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> product(){
        String sql="select * from product";//SQL查询语句
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }

}
