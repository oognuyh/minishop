package com.example.minishop.dao;

import com.example.minishop.mapper.OrderDetailMapper;
import com.example.minishop.model.OrderDetail;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderDetailDao {

    public List<OrderDetail> findOrderDetailsByOrderId(SqlSession session, int orderId) {
        return session.getMapper(OrderDetailMapper.class).findOrderDetailsByOrderId(orderId);
    }

    public int insert(SqlSession session, OrderDetail orderDetail) {
        return session.getMapper(OrderDetailMapper.class).insert(orderDetail);
    }
}
