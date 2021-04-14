package com.example.minishop.factory;

import com.example.minishop.config.OracleProperties;
import com.example.minishop.mapper.CartMapper;
import com.example.minishop.mapper.GoodsMapper;
import com.example.minishop.mapper.MemberMapper;
import com.example.minishop.mapper.OrderMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.sql.DataSource;
import java.io.InputStream;

public class OracleSqlSessionFactory {
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        Yaml yaml = new Yaml(new Constructor(OracleProperties.class));

        InputStream inputStream = OracleSqlSessionFactory.class
                .getClassLoader()
                .getResourceAsStream("config/oracle.yaml");

        OracleProperties properties = yaml.load(inputStream);

        DataSource dataSource = new PooledDataSource(
                properties.getDriver(),
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword()
        );

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(MemberMapper.class);
        configuration.addMapper(GoodsMapper.class);
        configuration.addMapper(CartMapper.class);
        configuration.addMapper(OrderMapper.class);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession(false);
    }
}
