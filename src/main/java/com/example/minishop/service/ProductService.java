package com.example.minishop.service;

import com.example.minishop.dao.ProductDao;
import com.example.minishop.factory.OracleSqlSessionFactory;
import com.example.minishop.model.Product;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductService {
    private final ProductDao productDao = new ProductDao();

    public List<Product> findProductsByCategoryId(int categoryId) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return productDao.findProductsByCategoryId(session, categoryId);
        }
    }

    public Product findProductById(int id) {
        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            return productDao.findProductById(session, id);
        }
    }
}
