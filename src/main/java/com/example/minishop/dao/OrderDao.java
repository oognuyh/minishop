package com.example.minishop.dao;

import com.example.minishop.mapper.OrderMapper;
import com.example.minishop.model.Order;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderDao {

    public List<Order> findAllOrders(SqlSession session) {
        return session.getMapper(OrderMapper.class).findAllOrders();
    }

    public List<Order> findOrdersByUserId(SqlSession session, String userid) {
        return  session.getMapper(OrderMapper.class).findOrdersByUserId(userid);
    }

    public int insertOrder(SqlSession session, Order order) {
        return session.getMapper(OrderMapper.class).insertOrders(order);
    }
}
