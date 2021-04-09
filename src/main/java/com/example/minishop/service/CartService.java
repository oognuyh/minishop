package com.example.minishop.service;

import com.example.minishop.dao.CartDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Cart;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CartService {
    private final CartDao cartDao = new CartDao();

    public Optional<List<Cart>> findAllCarts() {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
          return Optional.ofNullable(cartDao.findAllCarts(session));
        }
    }

    public Optional<List<Cart>> findCartsByUserId(String userid) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return Optional.ofNullable(cartDao.findCartsByUserId(session, userid));
        }
    }

    public boolean insertCart(Cart cart) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            Optional<Cart> existingCart = cartDao.findExistingCart(session, cart);

            if (existingCart.isPresent()) {
                if (cartDao.updateGAmountByNum(session, existingCart.get().getNum(), existingCart.get().getGAmount() + 1) == 1) {
                    session.commit();
                    return true;
                } else {
                    session.rollback();
                    return false;
                }
            } else {
                if (cartDao.insertCart(session, cart) == 1) {
                    session.commit();
                    return true;
                } else {
                    session.rollback();
                    return false;
                }
            }
        }
    }

    public boolean deleteCartByNum(int num) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            if (cartDao.deleteCartByNum(session, num) == 1) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        }
    }

    public boolean updateGAmountByNum(int num, int gAmount) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            if (cartDao.updateGAmountByNum(session, num, gAmount) == 1) {
                session.commit();
                return true;
            } else {
                session.rollback();
                return false;
            }
        }
    }
}
