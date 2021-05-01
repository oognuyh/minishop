package com.example.minishop.mapper;

import com.example.minishop.model.OrderDetail;
import com.example.minishop.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    @Select("select * from order_detail where order_id = #{orderId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productQuantity", column = "product_quantity"),
            @Result(property = "productColor", column = "product_color"),
            @Result(property = "productSize", column = "product_size"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "product", column = "product_id",
                    javaType = Product.class,
                    one = @One(select = "com.example.minishop.mapper.ProductMapper.findProductById", fetchType = FetchType.EAGER)),
    })
    List<OrderDetail> findOrderDetailsByOrderId(@Param("orderId") int orderId);

    @Insert("insert into order_detail (id, order_id, product_id, product_quantity, product_color, product_size, total_price) " +
            "values (#{id}, #{orderId}, #{productId}, #{productQuantity}, #{productColor}, #{productSize}, #{totalPrice})")
    int insert(OrderDetail orderDetail);
}
