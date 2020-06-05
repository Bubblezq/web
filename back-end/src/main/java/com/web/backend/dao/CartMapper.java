package com.web.backend.dao;

import com.web.backend.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface CartMapper {
    /**
     * 查询购物车中是否存在，若存在，不允许添加
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param productid
     * @param username
     * @return
     */
    @Select(value = "select c.id from cart c where c.productid=#{productid} and c.username=#{username}")
    @Results
            ({@Result(property = "id",column = "id")})
    Long findCartByName(@Param("productid") Long productid,@Param("username") String username);

    /**
     * 加入购物车  插入一条cart记录
     * @param productid
     * @param username
     * @return
     */
    //@Insert("insert into cart values(#{id},#{productname},#{picture},#{unitprice},#{username})")

    @Insert("insert into cart values(#{id},#{productid},#{username})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="id")
    void addCart(@Param("productid") Long productid,@Param("username") String username);

    @Insert("insert into myorder values(#{id},#{productid},1,#{username})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="id")
    void pay(@Param("productid") Long productid,@Param("username") String username);
}
