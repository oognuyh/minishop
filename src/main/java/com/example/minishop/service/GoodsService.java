package com.example.minishop.service;

import com.example.minishop.dao.GoodsDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Goods;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class GoodsService {
    private final GoodsDao goodsDao = new GoodsDao();

    public Optional<List<Goods>> findAllGoods() {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return Optional.ofNullable(goodsDao.findAllGoods(session));
        }
    }

    public Optional<List<Goods>> findGoodsByGCategory(String gCategory) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return Optional.ofNullable(goodsDao.findGoodsByGCategory(session, gCategory));
        }
    }

    public Optional<Goods> findGoodsByGCode(String gCode) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return goodsDao.findGoodsByGCode(session, gCode);
        }
    }
}
