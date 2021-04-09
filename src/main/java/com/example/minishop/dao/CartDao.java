package com.example.minishop.dao;

import com.example.minishop.mapper.CartMapper;
import com.example.minishop.model.Cart;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CartDao {

    public List<Cart> findAllCarts(SqlSession session) {
        return session.getMapper(CartMapper.class).findAllCarts();
    }

    public List<Cart> findCartsByUserId(SqlSession session, String userid) {
        return session.getMapper(CartMapper.class).findCartsByUserId(userid);
    }

    public Optional<Cart> findExistingCart(SqlSession session, Cart cart) {
        return session.getMapper(CartMapper.class).findExistingCart(cart);
    }

    public int insertCart(SqlSession session, Cart cart) {
        return session.getMapper(CartMapper.class).insertCart(cart);
    }

    public int updateGAmountByNum(SqlSession session, int num, int gAmount) {
        return session.getMapper(CartMapper.class).updateGAmountByNum(num, gAmount);
    }

    public int deleteCartByNum(SqlSession session, int num) {
        return session.getMapper(CartMapper.class).deleteCartByNum(num);
    }
}
