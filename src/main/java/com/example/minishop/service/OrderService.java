package com.example.minishop.service;

import com.example.minishop.dao.OrderDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Order;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();

    public List<Order> findAllOrders() {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return orderDao.findAllOrders(session);
        }
    }

    public List<Order> findOrdersByUserId(String userid) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return orderDao.findOrdersByUserId(session, userid);
        }
    }

    public boolean insertOrder(Order order) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            if (orderDao.insertOrder(session, order) == 1) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        }
    }
}
