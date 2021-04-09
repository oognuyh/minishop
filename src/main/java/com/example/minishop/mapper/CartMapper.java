package com.example.minishop.mapper;

import com.example.minishop.model.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    @Select("select * from cart")
    List<Cart> findAllCarts();

    @Select("select * from cart where userid = #{userid}")
    List<Cart> findCartsByUserId(@Param("userid") String userid);

    @Select("select * from cart where userid = #{userid} and gcode = #{gCode} and gcolor = #{gColor} and gsize = #{gSize}")
    Optional<Cart> findExistingCart(Cart cart);

    @Update("update cart set " +
            "gamount = #{gAmount}" +
            "where num = #{num}")
    int updateGAmountByNum(@Param("num") int num, @Param("gAmount") int gAmount);

    @Insert("insert into cart (num, userid, gcode, gname, gprice, gsize, gcolor, gamount, gimage) " +
            "values (cart_seq.nextval, #{userid}, #{gCode}, #{gName}, #{gPrice}, #{gSize}, #{gColor}, #{gAmount}, #{gImage})")
    int insertCart(Cart cart);

    @Delete("delete from cart where num = #{num}")
    int deleteCartByNum(@Param("num") int num);
}
