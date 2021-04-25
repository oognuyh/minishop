package com.example.minishop.service;

import com.example.minishop.dao.CartDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
public class CartService {
    private final CartDao cartDao = new CartDao();

    public List<Cart> findAllCarts() {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return cartDao.findAllCarts(session);
        }
    }

    public List<Cart> findCartsByMemberId(int memberId) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return cartDao.findCartsByMemberId(session, memberId);
        }
    }

    public boolean insert(Cart cart) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            cartDao.insert(session, cart);
            session.commit();
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    public boolean update(Cart cart) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            cartDao.update(session, cart);
            session.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Cart cart) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            cartDao.delete(session, cart);
            session.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
