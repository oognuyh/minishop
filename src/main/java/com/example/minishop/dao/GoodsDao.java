package com.example.minishop.dao;

import com.example.minishop.mapper.GoodsMapper;
import com.example.minishop.model.Goods;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class GoodsDao {

    public List<Goods> findAllGoods(SqlSession session) {
        return session.getMapper(GoodsMapper.class).findAllGoods();
    }

    public List<Goods> findGoodsByGCategory(SqlSession session, String gCategory) {
        return session.getMapper(GoodsMapper.class).findGoodsByGCategory(gCategory);
    }

    public Optional<Goods> findGoodsByGCode(SqlSession session, String gCode) {
        return session.getMapper(GoodsMapper.class).findGoodsByGCode(gCode);
    }
}
