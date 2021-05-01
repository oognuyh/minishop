package com.example.minishop.dao;

import com.example.minishop.mapper.OrderMapper;
import com.example.minishop.model.Order;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderDao {

    public List<Order> findOrdersByMemberId(SqlSession session, int memberId) throws Exception {
        return session.getMapper(OrderMapper.class).findOrdersByMemberId(memberId);
    }

    public int insert(SqlSession session, Order order) throws Exception {
        return session.getMapper(OrderMapper.class).insert(order);
    }
}
