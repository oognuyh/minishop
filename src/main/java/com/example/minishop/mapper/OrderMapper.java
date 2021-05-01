package com.example.minishop.mapper;

import com.example.minishop.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from order_history where member_id = #{memberId} order by id desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "paymentMethod", column = "payment_method"),
            @Result(property = "billingName", column = "billing_name"),
            @Result(property = "billingPhoneNumber", column = "billing_phone_number"),
            @Result(property = "billingZip", column = "billing_zip"),
            @Result(property = "billingAddress1", column = "billing_address1"),
            @Result(property = "billingAddress2", column = "billing_address2"),
            @Result(property = "shippingName", column = "shipping_name"),
            @Result(property = "shippingPhoneNumber", column = "shipping_phone_number"),
            @Result(property = "shippingZip", column = "shipping_zip"),
            @Result(property = "shippingAddress1", column = "shipping_address1"),
            @Result(property = "shippingAddress2", column = "shipping_address2"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "orderDetails", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.minishop.mapper.OrderDetailMapper.findOrderDetailsByOrderId", fetchType = FetchType.EAGER)),
    })
    List<Order> findOrdersByMemberId(@Param("memberId") int memberId);

    @Insert("insert into order_history (id, member_id, payment_method, billing_name, billing_phone_number, " +
            "billing_zip, billing_address1, billing_address2, shipping_name, shipping_phone_number, shipping_zip, " +
            "shipping_address1, shipping_address2, total_price)" +
            "values (#{id}, #{memberId}, #{paymentMethod}, #{billingName}, #{billingPhoneNumber}, " +
            "#{billingZip}, #{billingAddress1}, #{billingAddress2}, #{shippingName}, #{shippingPhoneNumber}, " +
            "#{shippingZip}, #{shippingAddress1}, #{shippingAddress2}, #{totalPrice})")
    @SelectKey(statement = "select order_history_seq.currval from dual", resultType = int.class, keyProperty = "id", before = false)
    int insert(Order order);
}
