package com.example.minishop.mapper;

import com.example.minishop.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("select * from orderinfo order by orderday desc")
    List<Order> findAllOrders();

    @Select("select num, userid, gcode, gname, gprice, gsize, gcolor, gamount, gimage, ordername, post, addr1, addr2, phone, paymethod, to_char(orderday, 'YYYY/MM/DD HH24:MI') as orderday from orderinfo where userid = #{userid} order by orderday desc")
    List<Order> findOrdersByUserId(@Param("userid") String userid);

    @Insert({
            "insert into orderinfo (num, userid, gcode, gname, gprice, gsize, gcolor, gamount, gimage, ordername, post, addr1, addr2, phone, paymethod, orderday)",
            "values (orderinfo_seq.nextval, #{userid}, #{gCode}, #{gName}, #{gPrice}, #{gSize}, #{gColor}, #{gAmount},",
            "#{gImage}, #{orderName}, #{post}, #{addr1}, #{addr2}, #{phone}, #{payMethod}, sysdate)",
    })
    int insertOrders(Order order);
}
