package com.example.minishop.dao;

import com.example.minishop.mapper.ProductMapper;
import com.example.minishop.model.Product;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductDao {

    public List<Product> findProductsByCategoryId(SqlSession session, int categoryId) {
        return session.getMapper(ProductMapper.class).findProductsByCategoryId(categoryId);
    }

    public Product findProductById(SqlSession session, int id) {
        return session.getMapper(ProductMapper.class).findProductById(id);
    }
}
