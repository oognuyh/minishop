package com.example.minishop.service;

import com.example.minishop.dao.OrderDao;
import com.example.minishop.dao.OrderDetailDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
public class OrderService {
    private final OrderDao orderDao = new OrderDao();
    private final OrderDetailDao orderDetailDao = new OrderDetailDao();

    public List<Order> findOrderByMemberId(int memberId) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return orderDao.findOrdersByMemberId(session, memberId);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public boolean insert(Order order) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            orderDao.insert(session, order);

            // session.commit();

            log.info("order : {}", order);

            order.getOrderDetails().forEach(orderDetail -> {
                orderDetail.setOrderId(order.getId());

                log.error("orderId: {}", order.getId());
                log.error("orderDetail : {}", orderDetail);

                orderDetailDao.insert(session, orderDetail);
            });

            session.commit();
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }
}
