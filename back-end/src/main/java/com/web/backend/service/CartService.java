package com.web.backend.service;

import com.web.backend.bean.Cart;
import com.web.backend.bean.User;
import com.web.backend.dao.CartMapper;
import com.web.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class CartService {
    @Autowired
    private CartMapper cartMapper;
    public CartService(CartMapper cartMapper){
        this.cartMapper=cartMapper;
    }

    public Result addCart(Cart cart){
        Result result = new Result();
        result.setSuccess(false);
        result.setData(null);
        try {
            Long existCartId = cartMapper.findCartByName(cart.getProductid(),cart.getUsername());
            if(existCartId != null){
                //如果商品在购物车中已存在
                result.setMsg("该商品已被添加到购物车");

            }else{
                cartMapper.addCart(cart.getProductid(),cart.getUsername());
                //System.out.println(user.getId());
                result.setMsg("成功加入购物车");
                result.setSuccess(true);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result pay(Cart cart){
        Result result = new Result();
        result.setSuccess(false);
        result.setData(null);
        try {
                cartMapper.pay(cart.getProductid(),cart.getUsername());
                //System.out.println(user.getId());
                result.setMsg("成功购买");
                result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
