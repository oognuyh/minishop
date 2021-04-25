package com.example.minishop.dao;

import com.example.minishop.mapper.CartMapper;
import com.example.minishop.model.Cart;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CartDao {

    public List<Cart> findAllCarts(SqlSession session) {
        return session.getMapper(CartMapper.class).findAllCarts();
    }

    public List<Cart> findCartsByMemberId(SqlSession session, int memberId) {
        return session.getMapper(CartMapper.class).findCartsByMemberId(memberId);
    }

    public int insert(SqlSession session, Cart cart) {
        return session.getMapper(CartMapper.class).insert(cart);
    }

    public int update(SqlSession session, Cart cart) {
        return session.getMapper(CartMapper.class).update(cart);
    }

    public int delete(SqlSession session, Cart cart) {
        return session.getMapper(CartMapper.class).delete(cart);
    }
}
