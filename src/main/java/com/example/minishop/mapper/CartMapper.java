package com.example.minishop.mapper;

import com.example.minishop.model.Cart;
import com.example.minishop.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select * from cart")
    List<Cart> findAllCarts();

    @Select("select * from cart where member_id = #{memberId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productQuantity", column = "product_quantity"),
            @Result(property = "productColor", column = "product_color"),
            @Result(property = "productSize", column = "product_size"),
            @Result(property = "product", column = "product_id",
                    javaType = Product.class,
                    one = @One(select = "com.example.minishop.mapper.ProductMapper.findProductById", fetchType = FetchType.EAGER))
    })
    List<Cart> findCartsByMemberId(@Param("memberId") int memberId);

    @Insert("insert into cart (id, member_id, product_id, product_quantity, product_color, product_size) " +
            "values (#{id}, #{memberId}, #{productId}, #{productQuantity}, #{productColor}, #{productSize})")
    int insert(Cart cart);

    @Update("update cart set " +
            "product_quantity = #{productQuantity}, " +
            "product_color = #{productColor}, " +
            "product_size = #{productSize} " +
            "where id = #{id}")
    int update(Cart cart);

    @Delete("delete from cart where id = #{id}")
    int delete(Cart cart);
}
